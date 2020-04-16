
package com.test.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Observable;


/**
 * Created by Administrator
 * on 2020/3/24.
 * Tree Cache：Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
 */
public class ZkTreeCacheListener extends Observable implements TreeCacheListener {

    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
        if (event.getType() == TreeCacheEvent.Type.NODE_UPDATED) {
            if (event.getData().getData() != null) {
                System.out.println("【TreeCacheListener】- 监听到ZK节点发生数据更新事件，节点路径：" + event.getData().getPath() + ",节点数据：" + new String(event.getData().getData()));
                setChanged();
                this.notifyObservers(event);
            }
        }
    }
}
