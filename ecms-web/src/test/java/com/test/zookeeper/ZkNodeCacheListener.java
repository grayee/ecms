
package com.test.zookeeper;

import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * Created by Administrator
 * on 2020/3/24.
 * Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地
 */
public class ZkNodeCacheListener implements NodeCacheListener {

    private NodeCache nodeCache;


    public ZkNodeCacheListener() {
    }

    public ZkNodeCacheListener(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    @Override
    public void nodeChanged() throws Exception {
        System.out.println("Node data is changed, new data: " +
                new String(nodeCache.getCurrentData().getData()));
    }
}
