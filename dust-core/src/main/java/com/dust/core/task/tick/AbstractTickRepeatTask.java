package com.dust.core.task.tick;

import com.dust.core.axis.TickAxis;
import com.dust.core.task.AbstractRepeatTask;
import com.dust.core.task.Task;

public abstract class AbstractTickRepeatTask extends AbstractRepeatTask {

    public AbstractTickRepeatTask(Task task, long initDelayFrame, long delayFrame, int repeatMaxCount) {
        super(task, initDelayFrame, delayFrame, repeatMaxCount, TickAxis.getInstance());
    }

}
