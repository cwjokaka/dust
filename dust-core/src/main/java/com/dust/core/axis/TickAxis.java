package com.dust.core.axis;

import com.dust.core.sys.TickSystem;

/**
 * 基于打点计数的时间轴
 */
public class TickAxis implements Axis {

    private TickAxis(){}

    private final static TickAxis INSTANCE = new TickAxis();

    public static Axis getInstance() {
        return INSTANCE;
    }

    @Override
    public long refreshTime(long delay) {
        return TickSystem.currentTick() + delay;
    }

    @Override
    public boolean isTimeUp(long current) {
        return TickSystem.currentTick() >= current;
    }

}
