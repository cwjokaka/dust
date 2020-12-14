package com.dust.core.task;

public abstract class AbstractRepeatTask extends AbstractScheduleTask {

    private final int repeatMaxCount;

    private int repeatCount = 0;

    public AbstractRepeatTask(Task task, long delayTime, int repeatMaxCount) {
        super(task, delayTime);
        this.repeatMaxCount = repeatMaxCount;
    }

    @Override
    public void refreshTime() {
        super.refreshTime();
        if (++repeatCount >= repeatMaxCount) {
            terminate();
        }
    }

}
