package com.test.design.pattern;

/**
 * Created by Administrator on 2019/8/2.
 */
public class AbstractFactoryTest {

    /**
     * cpu接口和实现类
     */
    public interface Cpu {
        String create();

        class Cpu650 implements Cpu {
            @Override
            public String create() {
                return "CPU-650";
            }
        }

        class Cpu825 implements Cpu {
            @Override
            public String create() {
                return "CPU-825";
            }
        }
    }

    /**
     * 屏幕接口和实现类
     */
    public interface Screen {

        String create();

        class Screen5 implements Screen {

            @Override
            public String create() {
                return "ScreenSize-5";
            }
        }

        class Screen6 implements Screen {

            @Override
            public String create() {
                return "ScreenSize-6";
            }
        }
    }

    /**
     * 工厂接口
     */
    public interface PhoneFactory {

        Cpu getCpu();//使用的cpu

        Screen getScreen();//使用的屏幕
    }

    /**
     * 具体工厂实现类：小米手机工厂
     */
    public static class XiaoMiFactory implements PhoneFactory {
        @Override
        public Cpu getCpu() {
            return new Cpu.Cpu825();//高性能处理器
        }

        @Override
        public Screen getScreen() {
            return new Screen.Screen6();//6寸大屏
        }
    }

    /**
     * 具体工厂实现类：红米手机工厂
     */
    public static class HongMiFactory implements PhoneFactory {

        @Override
        public Cpu getCpu() {
            return new Cpu.Cpu650();//高效处理器
        }

        @Override
        public Screen getScreen() {
            return new Screen.Screen5();//小屏手机
        }
    }

    public static void main(String[] args) {
        PhoneFactory hongMiFactory = new HongMiFactory();
        PhoneFactory xiaoMiFactory = new XiaoMiFactory();
        System.out.println(hongMiFactory.getCpu().create()+"+" +hongMiFactory.getScreen().create());
        System.out.println(xiaoMiFactory.getCpu().create()+"+" +xiaoMiFactory.getScreen().create());
    }


}
