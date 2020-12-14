package com.dust.core.sys;

/**
 * 全局帧数管理器
 */
public final class FrameSystem {

    private static long currentFrame = 0;

    public static long currentFrame() {
        return currentFrame;
    }

    public static void increaseFrame() {
        currentFrame++;
    }

}
