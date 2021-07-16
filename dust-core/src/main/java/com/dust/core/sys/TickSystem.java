package com.dust.core.sys;

/**
 * 全局帧数管理器
 */
public final class TickSystem {

    /**
     * 当前帧
     */
    private static long currentTick = 0;

    /**
     * @return 当前帧数
     */
    public static long currentTick() {
        return currentTick;
    }

    /**
     * 向前至下一帧
     */
    public static void increaseTick() {
        currentTick++;
    }

}
