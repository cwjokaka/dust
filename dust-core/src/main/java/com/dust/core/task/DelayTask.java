package com.dust.core.task;

/**
 * 延时任务
 */
public interface DelayTask extends Task, Terminable, TaskRef {

    /**
     * 是否到执行时间点
     * @return 布尔值
     */
    boolean isTimeUp();

    /**
     * 重置执行时间
     */
    void resetTime();

    /**
     * 是否可执行
     * @return 布尔值
     */
    default boolean runnable() {
        return true;
    }

    /**
     * 是否可重复
     */
    default boolean repeatable() {
        return false;
    }

}
