package com.dust.core.task;

/**
 * 定时任务
 */
public interface ScheduleTask extends DelayTask {

    /**
     * 刷新下次执行的时间
     */
    void refreshTime();

}
