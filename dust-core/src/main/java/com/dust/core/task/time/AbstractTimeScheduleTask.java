package com.dust.core.task.time;

import com.dust.core.axis.TimeAxis;
import com.dust.core.task.AbstractScheduleTask;
import com.dust.core.task.Task;

public abstract class AbstractTimeScheduleTask extends AbstractScheduleTask {

    public AbstractTimeScheduleTask(Task task, long initDelayTime, long delayTime) {
        super(task, initDelayTime, delayTime, TimeAxis.getInstance());
    }

}
