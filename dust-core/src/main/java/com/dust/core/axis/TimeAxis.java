package com.dust.core.axis;

/**
 * 基于时间的时间轴
 */
public class TimeAxis implements Axis {

    private TimeAxis(){}

    private final static TimeAxis INSTANCE = new TimeAxis();

    public static Axis getInstance() {
        return INSTANCE;
    }

    @Override
    public long refreshTime(long delay) {
        return System.currentTimeMillis() + delay;
    }

    @Override
    public boolean isTimeUp(long current) {
        return System.currentTimeMillis() >= current;
    }

}
