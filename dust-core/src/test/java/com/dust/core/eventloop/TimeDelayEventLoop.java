package com.dust.core.eventloop;

import com.dust.core.eventloop.impl.DustEventLoop;

public class TimeDelayEventLoop extends DustEventLoop {
    public TimeDelayEventLoop(long refreshCycle) {
        super(refreshCycle);
    }
}
