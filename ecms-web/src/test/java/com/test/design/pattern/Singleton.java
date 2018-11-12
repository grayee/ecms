package com.test.design.pattern;

/**
 * 单例模式
 *
 * 懒汉式与饿汉式的根本区别在与是否在类内方法外创建自己的对象。
 * 并且声明对象都需要私有化，构造方法都要私有化，这样外部才不能通过 new 对象的方式来访问。
 * 饿汉式的话是声明并创建对象(因为他饿)，懒汉式的话只是声明对象，在调用该类的 getinstance() 方法时才会进行 new 对象。
 *
 * @author Gray.Z
 * @date 2018/11/12.
 */
public class Singleton {

    /**
     * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程,
     * Singleton 类被装载了，instance 不一定被初始化。因为 SingletonHolder 类没有被主动使用，
     * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
     */
    private static class SingletonHolder {

        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    /**
     * 静态工厂方法 getInstance() 的性能对应用程序很关键
     */
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello Singleton!");
    }

    /**
     * Effective Java 作者 Josh Bloch 提倡
     */
    public enum SingletonEnum {
        INSTANCE;

        public void showMessage() {
            System.out.println("Hello Singleton Enum!");
        }
    }

    /**
     * 双检锁/双重校验锁（DCL，即 double-checked locking）
     */
    public static class DclSingleton {

        private volatile static DclSingleton singleton;

        private DclSingleton() {
        }

        public static DclSingleton getInstance() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new DclSingleton();
                    }
                }
            }
            return singleton;
        }

        public void showMessage() {
            System.out.println("Hello Singleton DCL!");
        }

    }

    public static void main(String[] args) {
        Singleton.getInstance().showMessage();
        DclSingleton.getInstance().showMessage();
        SingletonEnum.INSTANCE.showMessage();
    }
}
