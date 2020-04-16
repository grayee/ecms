
package com.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator
 * on 2020/3/24.
 */
public class ZkCurator {
    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;
    private CuratorFramework client = null;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ZkCurator(String connectAddr, String baseWatchPath) {
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        this.client = CuratorFrameworkFactory.builder()
                .connectString(connectAddr)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .namespace(baseWatchPath)// 命名空间
                .build();
        client.start();
    }

    public void watchChild(String path) {
        //TreeCacheListener，循环监听子节点
        TreeCache cache = new TreeCache(client, path);
        ZkTreeCacheListener listener = new ZkTreeCacheListener();
        listener.addObserver(new XmlWebAppConfigUpdater());
        cache.getListenable().addListener(listener, executorService);
        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关闭客户端
    public void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }

    public boolean isZKAlive() {
        return client != null && client.getState() == CuratorFrameworkState.STARTED;
    }


    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }
}
