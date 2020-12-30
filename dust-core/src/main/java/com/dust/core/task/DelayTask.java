package com.dust.core.task;

/**
 * 延时任务
 */
public interface DelayTask extends Task, Terminable {

    /**
     * 是否到执行时间点
     * @return 布尔值
     */
    boolean isTimeUp();

    /**
     * 是否可执行
     * @return 布尔值
     */
    default boolean isRunnable() {
        return true;
    }

    /**
     * 是否可重复
     */
    default boolean isRepeatable() {
        return false;
    }

}
