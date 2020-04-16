package com.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangruigang on 2018/1/5.
 * Zookeeper 从设计模式角度来看，是一个基于观察者模式设计的分布式服务管理框架，它负责存储和管理大家都关心的数据，然后接受观察者的注册，
 * 一旦这些数据的状态发生变化，Zookeeper 就将负责通知已经在 Zookeeper 上注册的那些观察者做出相应的反应，从而实现集群中类似 Master/Slave 管理模式， 关于
 * Zookeeper 的详细架构等内部细节可以阅读 Zookeeper 的源码 https://www.ibm.com/developerworks/cn/opensource/os-cn-zookeeper/index.html
 * http://www.cnblogs.com/leesf456/p/6239578.html
 * https://www.iteye.com/blog/coolxing-1871009
 * https://www.cnblogs.com/erbing/p/9799098.html
 */
public class ZookeeperTest {

    private static final String ZK_LOCK_PATH = "/zktest";

    //ZooKeeper服务地址
    private static final String CONNECT_ADDR = "172.16.14.112:2181,172.16.14.112:2182,172.16.14.112:2183";
    private static ZkCurator curator = new ZkCurator(CONNECT_ADDR, ZK_LOCK_PATH);


    public static void main(String[] args) throws Exception {
        testZookeeper();

        CuratorFramework client = curator.getClient();
        testZKPaths(client);

        String path_test = "/path";

        PathChildrenCache cache = new PathChildrenCache(client, path_test, true);
        cache.start();
        PathChildrenCacheListener cacheListener = (client1, event) -> {
            System.out.println("事件类型：" + event.getType());
            if (null != event.getData()) {
                System.out.println("节点数据：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(cacheListener);

        if (client.checkExists().forPath(path_test) != null) {
            System.out.println("============>>>" + client.getData().forPath(path_test));
            client.delete().forPath(path_test);
        } else {
            client.create().withMode(CreateMode.PERSISTENT).forPath(path_test, "init".getBytes());
        }

        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
    }

    /**
     * Curator工具类 ZKPaths　提供了简单的API来构建znode路径、递归创建、删除节点等。
     *
     * @param client
     * @throws Exception
     */
    private static void testZKPaths(CuratorFramework client) throws Exception {
        ZooKeeper zookeeper = client.getZookeeperClient().getZooKeeper();
        String path = "/curator_zkpath_sample";
        System.out.println(ZKPaths.fixForNamespace(path, "sub"));
        System.out.println(ZKPaths.makePath(path, "sub"));
        System.out.println(ZKPaths.getNodeFromPath("/curator_zkpath_sample/sub1"));

        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode("/curator_zkpath_sample/sub1");
        System.out.println(pn.getPath());
        System.out.println(pn.getNode());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zookeeper, dir1);
        ZKPaths.mkdirs(zookeeper, dir2);
        System.out.println(ZKPaths.getSortedChildren(zookeeper, path));

        ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(), path, true);
    }

    private static void testZookeeper() throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, 100, new Watcher() {
            // 监控所有被触发的事件
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("已经触发了" + watchedEvent.getType() + "事件！" + watchedEvent.getPath());
            }
        });

        String path = "/testRootPath";

        /**
         * 创建一个节点,模式是PERSISTENT;CreateMode 标识有四种形式的目录节点，分别是
         * 1.持久节点-PERSISTENT：节点创建后便一直存在于Zookeeper服务器上，直到有删除操作来主动清楚该节点，这个目录节点存储的数据不会丢失；
         * 2.持久顺序节点-PERSISTENT_SEQUENTIAL：顺序自动编号的目录节点，这种目录节点会根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名；
         * 3.临时节点-EPHEMERAL(ephemeral)：临时节点的生命周期与客户端会话绑定，客户端失效，节点会被自动清理。同时，Zookeeper规定不能基于临时节点来创建子节点，即临时节点只能作为叶子节点。
         * 4.临时顺序节点-EPHEMERAL_SEQUENTIAL：在临时节点的基础添加了顺序特性。
         */
        zk.create(path, "test path data1...".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建根目录节点" + path + ",数据为：" + new String(zk.getData(path, true, null)));

        String childPath = path + "/testChildPathOne";
        // 创建一个子目录节点
        zk.create(childPath, "testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 取出子目录节点列表
        System.out.println("子目录节点:" + zk.getChildren(path, true));

        // 修改子目录节点数据
        zk.setData(childPath, "modifyChildDataOne".getBytes(), -1);
        System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");

        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));

        // 删除子目录节点
        zk.delete("/testRootPath/testChildPathTwo", -1);
        zk.delete("/testRootPath/testChildPathOne", -1);

        //修改节点数据
        zk.setData(path, "test path data2".getBytes(), -1);
        System.out.println("修改节点" + path + ",数据为：" + new String(zk.getData(path, null, null)));
        // 删除父目录节点
        System.out.println(zk.exists(path, null));
        zk.delete(path, -1);
        //节点是否存在
        System.out.println(zk.exists(path, null));

        // 关闭连接
        zk.close();
    }

    /**
     * 事务管理：碰到异常，事务会回滚
     *
     * @throws Exception
     */
    public void testTransaction() throws Exception {
        CuratorFramework client = curator.getClient();
        //定义几个基本操作
        CuratorOp createOp = client.transactionOp().create()
                .forPath("/curator/one_path", "some data".getBytes());

        CuratorOp setDataOp = client.transactionOp().setData()
                .forPath("/curator", "other data".getBytes());

        CuratorOp deleteOp = client.transactionOp().delete()
                .forPath("/curator");

        //事务执行结果
        List<CuratorTransactionResult> results = client.transaction()
                .forOperations(createOp, setDataOp, deleteOp);

        //遍历输出结果
        for (CuratorTransactionResult result : results) {
            System.out.println("执行结果是： " + result.getForPath() + "--" + result.getType());
        }
    }

    /**
     * 分布式锁
     *
     * @param client client
     */
    private static void doWithLock(CuratorFramework client) {
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " hold lock");
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
