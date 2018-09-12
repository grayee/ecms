package disruptor;

/**
 * Created by zhangruigang on 2017/1/14.
 */

import com.lmax.disruptor.EventFactory;

/**
 * 事件生产工厂
 *
 * @author wanghao
 */
public class LongEventFactory implements EventFactory<LongEvent> {


    public LongEvent newInstance() {
        return new LongEvent();
    }

}
