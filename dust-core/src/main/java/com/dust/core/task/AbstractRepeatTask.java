package com.dust.core.task;

import com.dust.core.axis.Axis;

public abstract class AbstractRepeatTask extends AbstractScheduleTask implements RepeatTask {

    /**
     * 最大重复次数
     */
    private final int repeatMaxCount;

    public AbstractRepeatTask(Task task, long initDelay, long delay, int repeatMaxCount, Axis axis) {
        super(task, initDelay, delay, axis);
        this.repeatMaxCount = repeatMaxCount;
    }

    @Override
    public void resetTime() {
        super.resetTime();
        if (isRepeatFinish()) {
            terminate();
        }
    }

    @Override
    public boolean isRepeatFinish() {
        return executedTimes >= repeatMaxCount;
    }

}
