package com.qslion.framework.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gray.Z
 * @date 2018/4/21 17:01.
 */
public class CGlibMapper {
    //使用缓存提高效率
    private static final ConcurrentHashMap<String, BeanCopier> mapCaches = new ConcurrentHashMap<>();

    public static <O, T> T mapper(O source, Class<T> target) {
        T instance = baseMapper(source, target);
        return instance;
    }

    public static <O, T> T mapper(O source, Class<T> target, IAction<T> action) {
        T instance = baseMapper(source, target);
        action.run(instance);
        return instance;
    }

    public static <O, T> T mapperObject(O source, T target) {
        String baseKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!mapCaches.containsKey(baseKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            mapCaches.put(baseKey, copier);
        } else {
            copier = mapCaches.get(baseKey);
        }
        copier.copy(source, target, null);
        return target;
    }

    public static <O, T> T mapperObject(O source, T target, IAction<T> action) {
        String baseKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!mapCaches.containsKey(baseKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            mapCaches.put(baseKey, copier);
        } else {
            copier = mapCaches.get(baseKey);
        }
        copier.copy(source, target, null);
        action.run(target);
        return target;
    }

    private static <O, T> T baseMapper(O source, Class<T> target) {
        String baseKey = generateKey(source.getClass(), target);
        BeanCopier copier;
        if (!mapCaches.containsKey(baseKey)) {
            copier = BeanCopier.create(source.getClass(), target, false);
            mapCaches.put(baseKey, copier);
        } else {
            copier = mapCaches.get(baseKey);
        }
        T instance = null;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // value 源对象属性，target 目标对象属性类，context 目标对象setter方法名
        copier.copy(source, instance, (value,tar,context)->{
            return value;
        });
        return instance;
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    @FunctionalInterface
    public interface IAction<T> {
        void run(T param);
    }
}

