package com.test;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhangruigang on 2017/9/20.
 *
 * 观察者设计模式: 如果想要实现观察者模式，则必须依靠java.util包中提供的Observable类和Observer接口
 *
 * 现在很多的购房者都在关注着房子的价格变化，每当房子价格变化的时候，所有的购房者都可以观察得到。
 * 实际上以上的购房者就是观察者，他们所关注的房价就是被观察者
 * 其中要求，被观察者需要继承Observable类，观察则需要实现Observer接口
 */
public class ObserverTest {


    @Test
    public void test() {
        House house = new House(10000);
        HousePriceObserver A = new HousePriceObserver("A");
        HousePriceObserver B = new HousePriceObserver("B");
        HousePriceObserver C = new HousePriceObserver("C");
        house.addObserver(A);
        house.addObserver(B);
        house.addObserver(C);
        System.out.println(house);
        house.setPrice(6000);
        house.setPrice(8000);
    }

    /**
     * 房价的实现(被观察者,继承Observable)
     */
    class House extends Observable {

        private double price;

        public House(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (this.price != price) {
                this.price = price;
                //标注价格已经被更改
                setChanged();
                //通知观察者数据已被更改
                this.notifyObservers(price);
            }
        }

        @Override
        public String toString() {
            return "当前房价为：" + price;
        }
    }

    /**
     * 购房者实现，（观察者，实现Observer接口）
     */
    class HousePriceObserver implements Observer {

        private String name;

        public HousePriceObserver(String name) {
            this.name = name;
        }

        @Override
        public void update(Observable o, Object arg) {
            //这里最好判断一下通知是否来自于房价，有可能来自其它地方
            if (o instanceof House) {
                System.out.println("购物者" + name + "观察到房价已调整为：" + arg);
            }
        }
    }
}
