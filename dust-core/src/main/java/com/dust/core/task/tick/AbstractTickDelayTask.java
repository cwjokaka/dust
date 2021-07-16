package com.dust.core.task.tick;

import com.dust.core.axis.TickAxis;
import com.dust.core.task.AbstractDelayTask;
import com.dust.core.task.Task;

/**
 * 基于打点计数的延时任务
 */
public abstract class AbstractTickDelayTask extends AbstractDelayTask {

    public AbstractTickDelayTask(Task task, long initDelayFrame, long delayFrame) {
        super(task, initDelayFrame, delayFrame, TickAxis.getInstance());
    }

}
