package com.dust.core.task;

import com.dust.core.axis.Axis;

public abstract class AbstractScheduleTask extends AbstractDelayTask implements ScheduleTask {

    public AbstractScheduleTask(Task task, long delayTime, Axis axis) {
        super(task, delayTime, axis);
    }

    @Override
    public void refreshTime() {
        this.nextExecuteTime = axis.refreshTime(this.delayTime);
    }

}
