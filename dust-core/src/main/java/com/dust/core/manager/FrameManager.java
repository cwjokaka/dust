package com.dust.core.manager;

/**
 * 帧数管理
 */
public final class FrameManager {

    private static long currentFrame = 0;

    public static long currentFrame() {
        return currentFrame;
    }

    public static void increaseFrame() {
        currentFrame++;
    }

}
