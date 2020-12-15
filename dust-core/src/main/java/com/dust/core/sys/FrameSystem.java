package com.dust.core.sys;

/**
 * 全局帧数管理器
 */
public final class FrameSystem {

    /**
     * 当前帧
     */
    private static long currentFrame = 0;

    /**
     * @return 当前帧数
     */
    public static long currentFrame() {
        return currentFrame;
    }

    /**
     * 向前至下一帧
     */
    public static void increaseFrame() {
        currentFrame++;
    }

}
