package com.dust.core.event;

import com.dust.core.eventloop.impl.DustEventLoop;

public class EventLoopDoNothing extends DustEventLoop {
    public EventLoopDoNothing(long refreshCycle) {
        super(refreshCycle);
    }
}
