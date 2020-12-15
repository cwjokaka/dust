package com.dust.core.axis;

import com.dust.core.sys.FrameSystem;

/**
 * 基于帧的时间轴
 */
public class FrameAxis implements Axis {

    private FrameAxis(){}

    private final static FrameAxis INSTANCE = new FrameAxis();

    public static Axis getInstance() {
        return INSTANCE;
    }

    @Override
    public long refreshTime(long delay) {
        return FrameSystem.currentFrame() + delay;
    }

    @Override
    public boolean isTimeUp(long current) {
        return FrameSystem.currentFrame() >= current;
    }

}
