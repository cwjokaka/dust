package com.dust.core.event;

import com.dust.core.annotation.MainEventQueue;
import com.dust.core.eventloop.impl.DustEventLoop;

@MainEventQueue
public class EventLoopDoNothing extends DustEventLoop {
    public EventLoopDoNothing(long refreshCycle) {
        super(refreshCycle);
    }
}
