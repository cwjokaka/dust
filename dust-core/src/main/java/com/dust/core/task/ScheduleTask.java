package com.dust.core.task;

/**
 * 定时任务
 */
public interface ScheduleTask extends DelayTask {

    /**
     * 是否可重复
     */
    @Override
    default boolean repeatable() {
        return true;
    }

}
