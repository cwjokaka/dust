package com.dust.core.task;

import com.dust.core.axis.Axis;

public abstract class AbstractScheduleTask extends AbstractDelayTask implements ScheduleTask {


    public AbstractScheduleTask(Task task, long initDelay, long delay, Axis axis) {
        super(task, initDelay, delay, axis);
    }

}
