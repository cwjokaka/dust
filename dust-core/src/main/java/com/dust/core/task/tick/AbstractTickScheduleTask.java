package com.dust.core.task.tick;

import com.dust.core.axis.TickAxis;
import com.dust.core.task.AbstractScheduleTask;
import com.dust.core.task.Task;

public abstract class AbstractTickScheduleTask extends AbstractScheduleTask {

    public AbstractTickScheduleTask(Task task, long initDelayFrame, long delayFrame) {
        super(task, initDelayFrame, delayFrame, TickAxis.getInstance());
    }

}
