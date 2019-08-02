package com.test.design.pattern;

import com.test.design.pattern.FactoryTest.*;

/**
 * Created by Administrator on 2019/8/1.
 */
public class FactoryMethodTest {

    /**
     * 抽象工厂类-工厂接口
     */
    public interface FruitFactory {
        Fruit createFruit();//生产水果
    }

    /**
     * 具体工厂类-苹果工厂
     */
    public static class AppleFactory implements FruitFactory {
        @Override
        public Fruit createFruit() {
            return new Apple();
        }
    }

    /**
     * 具体工厂类-梨工厂
     */
    public static class PearFactory implements FruitFactory {
        @Override
        public Fruit createFruit() {
            return new Pear();
        }
    }

    public static void main(String[] args) {
        //具体使用
        AppleFactory appleFactory = new AppleFactory();
        PearFactory pearFactory = new PearFactory();
        Apple apple = (Apple) appleFactory.createFruit();//获得苹果
        Pear pear = (Pear) pearFactory.createFruit();//获得梨
        System.out.println(apple.getCategory());
        System.out.println(pear.getCategory());
    }
}
