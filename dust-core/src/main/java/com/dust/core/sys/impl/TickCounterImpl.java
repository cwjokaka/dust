package com.dust.core.sys.impl;

import com.dust.core.sys.TickCounter;

public class TickCounterImpl implements TickCounter {

    private long currentTick = 0;

    @Override
    public long currentTick() {
        return currentTick;
    }

    @Override
    public void increaseTick() {
        currentTick++;
    }

}
