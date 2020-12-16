package com.dust.core.task;

import com.dust.core.axis.Axis;

public abstract class AbstractRepeatTask extends AbstractScheduleTask implements RepeatTask {

    /**
     * 最大重复次数
     */
    private final int repeatMaxCount;

    /**
     * 当前重复次数
     */
    private int repeatCount = 0;

    public AbstractRepeatTask(Task task, long initDelay, long delay, int repeatMaxCount, Axis axis) {
        super(task, initDelay, delay, axis);
        this.repeatMaxCount = repeatMaxCount;
    }

    @Override
    public void increaseRepeatCount() {
        if (++repeatCount >= repeatMaxCount) {
            terminate();
        }
    }

    @Override
    public void refreshTime() {
        super.refreshTime();
        increaseRepeatCount();
    }

    @Override
    public boolean isRepeatFinish() {
        return repeatCount >= repeatMaxCount;
    }

}
