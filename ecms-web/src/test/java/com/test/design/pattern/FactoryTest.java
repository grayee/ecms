package com.test.design.pattern;

/**
 * Created by Administrator on 2019/8/1.
 */
public class FactoryTest {

    /**
     * 抽象产品-水果接口
     */
    public interface Fruit {
        String getCategory();
    }

    /**
     * 具体实现类-苹果
     */
    public static class Apple implements Fruit {
        @Override
        public String getCategory() {
            return "apple";
        }
    }

    /**
     * 具体实现类-梨
     */
    public static class Pear implements Fruit {
        @Override
        public String getCategory() {
            return "pear";
        }
    }

    /**
     * 具体工厂-水果工厂类
     */
    public static class FruitFactory {
        Fruit createFruit(String type) {
            Fruit fruit;
            switch (type) {
                case "apple":
                    fruit = new Apple();
                    break;
                case "pear":
                    fruit = new Pear();
                    break;
                default:
                    fruit = new Apple();
                    break;
            }
            return fruit;
        }
    }

    public static void main(String[] args) {
        //产品使用者
        FruitFactory mFactory = new FruitFactory();
        System.out.println(mFactory.createFruit("apple").getCategory());
        System.out.println(mFactory.createFruit("pear").getCategory());
    }
}
