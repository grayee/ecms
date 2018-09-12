package disruptor;

/**
 * Created by zhangruigang on 2017/1/14.
 */

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {

    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("事件值："+event.get());
    }

    public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
        event.set(buffer.getLong(0));
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // 执行器，用于构造消费者线程
        //Executor executor = Executors.newCachedThreadPool(DaemonThreadFactory.INSTANCE);

      /*  // 指定事件工厂
        LongEventFactory factory = new LongEventFactory();*/

        // 指定 ring buffer字节大小, must be power of 2.
        int bufferSize = 1024;

     /*   //单线程模式，获取额外的性能
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,
                bufferSize, executor,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

         //设置事件业务处理器---消费者
        disruptor.handleEventsWith(new LongEventHandler());

                */
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent.FACTORY, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(LongEventMain::handleEvent);
        //启动disruptor线程
        // 获取 ring buffer环，用于接取生产者生产的事件
        RingBuffer<LongEvent> ringBuffer =  disruptor.start();
        //为 ring buffer指定事件生产者
        //LongEventProducer producer = new LongEventProducer(ringBuffer);
        //LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);//预置8字节长整型字节缓存
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            //producer.onData(bb);//生产者生产数据
            ringBuffer.publishEvent(LongEventMain::translate, bb);
            //Thread.sleep(100);
        }

    }
}