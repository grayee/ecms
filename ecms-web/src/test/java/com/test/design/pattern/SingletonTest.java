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
public class SingletonTest {


    /**
     * 饿汉式单例
      */
    public static class HungrySingleton {
        // 私有构造
        private HungrySingleton() {}

        private static HungrySingleton single = new HungrySingleton();

        // 静态工厂方法
        public static HungrySingleton getInstance() {
            System.out.println("HungrySingleton getInstance...");
            return single;
        }
    }

    /**
     * 懒汉式单例-非线程安全
     */
    public static class LazySingleton {

        // 私有构造
        private LazySingleton() { }

        private static LazySingleton single;

        public static LazySingleton getInstance() {
            if (single == null) {
                single = new LazySingleton();
            }
            System.out.println("LazySingleton getInstance...");
            return single;
        }
    }

    /**
     * 懒汉式单例-线程安全
     */
    public static class LazySyncSingleton {

        // 私有构造
        private LazySyncSingleton() { }

        private static LazySyncSingleton single;

        public static synchronized LazySyncSingleton getInstance() {
            if (single == null) {
                single = new LazySyncSingleton();
            }
            System.out.println("LazySyncSingleton getInstance...");
            return single;
        }
    }

    /**
     * 静态内部类实现单例-登记式
     */
    public static class RegisterSingleton  {
        // 私有构造
        private RegisterSingleton() {
        }

        /**
         * 静态内部类
         * <p>
         * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程,
         * Singleton 类被装载了，instance 不一定被初始化。因为 SingletonHolder 类没有被主动使用，
         * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
         */
        private static class SingletonHolder {
            private static final RegisterSingleton INSTANCE = new RegisterSingleton();
        }

        public static RegisterSingleton getInstance() {
            System.out.println("RegisterSingleton getInstance");
            return SingletonHolder.INSTANCE;
        }
    }

    public static class StaticBlockSingleton {
        // 私有构造
        private StaticBlockSingleton() {
        }

        private static StaticBlockSingleton single = null;

        // 静态代码块
        static {
            single = new StaticBlockSingleton();
        }

        public static StaticBlockSingleton getInstance() {
            return single;
        }
    }

    /**
     * Effective Java 作者 Josh Bloch 提倡
     */
    public enum EnumSingleton {
        INSTANCE;
        public void showMessage() {
            System.out.println("Hello EnumSingleton");
        }
    }

    /**
     * 双检锁/双重校验锁（DCL，即 double-checked locking）
     */
    public static class LazyDclSingleton {

        private volatile static LazyDclSingleton singleton;

        private LazyDclSingleton() {
        }

        public static LazyDclSingleton getInstance() {
            if (singleton == null) {
                synchronized (LazyDclSingleton.class) {
                    if (singleton == null) {
                        singleton = new LazyDclSingleton();
                    }
                }
            }
            System.out.println("LazyDclSingleton getInstance...");
            return singleton;
        }
    }

    public static void main(String[] args) {
        HungrySingleton.getInstance();
        LazySingleton.getInstance();
        LazySyncSingleton.getInstance();
        LazyDclSingleton.getInstance();
        RegisterSingleton.getInstance();
        StaticBlockSingleton.getInstance();
        EnumSingleton.INSTANCE.showMessage();
    }
}
