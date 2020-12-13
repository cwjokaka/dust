package com.dust.core.task;

/**
 * 默认的计划任务
 */
public class DefaultScheduleTask extends AbstractDelayTask implements ScheduleTask {

    public DefaultScheduleTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public void refreshTime() {
        this.endTime = System.currentTimeMillis() + this.delayTime;
    }

}
