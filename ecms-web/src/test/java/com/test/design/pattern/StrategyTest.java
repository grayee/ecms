package com.test.design.pattern;

/**
 * Created by Administrator
 * on 2019/12/20.
 */
public class StrategyTest {

    /**
     * 策略接口
     */
    public interface IStrategy {
        void testStrategy();
    }

    /**
     * 策略接口实现：打印策略
     */
    public static class PrintStrategy implements IStrategy {
        public void testStrategy() {
            System.out.print("我要打印！！");
        }
    }

    /**
     * 策略接口实现：写字策略
     */
    public class WriteStrategy implements IStrategy {
        public void testStrategy() {
            System.out.println("我要写字！！！");
        }
    }

    /**
     * 策略执行上下文
     */
    public static class Context {
        private IStrategy stg;

        Context(IStrategy theStg) {
            this.stg = theStg;
        }

        void doAction() {
            this.stg.testStrategy();
        }
    }

    public static void main(String[] args) {
        //客户端通过上下文调用策略
        IStrategy stgA = new PrintStrategy();
        Context ct = new Context(stgA);
        ct.doAction();
    }
}
