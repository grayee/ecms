package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhangruigang on 2017/1/14.
 */
public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return this.value;
    }

    public static final EventFactory<LongEvent> FACTORY = new EventFactory<LongEvent>() {
        @Override
        public LongEvent newInstance() {
            return new LongEvent();
        }
    };
}
