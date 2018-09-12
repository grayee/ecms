package disruptor;

/**
 * Created by zhangruigang on 2017/1/14.
 */

import com.lmax.disruptor.EventHandler;

/**
 * 消息者事件处理器，打印输出到控制台
 *
 * @author harry
 *         a consumer that will handle these events
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("consumer:" + Thread.currentThread().getName() + " Event: value=" + event.get() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}