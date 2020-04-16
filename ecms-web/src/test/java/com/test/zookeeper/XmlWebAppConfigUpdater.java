
package com.test.zookeeper;

import com.qslion.framework.util.SpringUtil;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator
 * on 2020/3/24.
 */
public class XmlWebAppConfigUpdater implements Observer {


    /**
     * 刷新web容器
     *
     * @return
     */
    public boolean refresh() {
        XmlWebApplicationContext applicationContext = (XmlWebApplicationContext) ContextLoader.getCurrentWebApplicationContext();

        applicationContext.refresh();

        System.out.println("spring IOC容器重启成功。。。");
        return true;
    }

    public <T> T registerBean(String name, Class<T> clazz, Object... args) {
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringUtil.getApplicationContext();
        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return (T) bean;
            } else {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        if (args.length > 0) {
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();


        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return applicationContext.getBean(name, clazz);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ZkTreeCacheListener) {
            TreeCacheEvent event = (TreeCacheEvent) arg;
            System.out.println("ZK配置已更新为：" + new String(event.getData().getData()) + ",开始更新properties配置文件...");
            refresh();
        }
    }

}
