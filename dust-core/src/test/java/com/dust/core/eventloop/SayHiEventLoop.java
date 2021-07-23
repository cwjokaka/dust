package com.dust.core.eventloop;


import com.dust.core.eventloop.impl.DustEventLoop;

public class SayHiEventLoop extends DustEventLoop {

    public SayHiEventLoop(long refreshCycle) {
        super(refreshCycle);
    }

    @Override
    protected void executeEachTick() {
        System.out.println("HI!");
    }

}
