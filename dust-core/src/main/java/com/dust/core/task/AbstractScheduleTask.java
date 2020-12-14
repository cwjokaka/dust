package com.dust.core.task;

public abstract class AbstractScheduleTask extends AbstractDelayTask implements ScheduleTask {

    public AbstractScheduleTask(Task task, long delayTime) {
        super(task, delayTime);
    }

    @Override
    public void refreshTime() {
        this.nextExecuteTime = System.currentTimeMillis() + this.delayTime;
    }

}
