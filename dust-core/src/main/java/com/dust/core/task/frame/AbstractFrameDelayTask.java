package com.dust.core.task.frame;

import com.dust.core.axis.FrameAxis;
import com.dust.core.task.AbstractDelayTask;
import com.dust.core.task.Task;

/**
 * 基于帧的延时任务
 */
public abstract class AbstractFrameDelayTask extends AbstractDelayTask {

    public AbstractFrameDelayTask(Task task, long initDelayFrame, long delayFrame) {
        super(task, initDelayFrame, delayFrame, FrameAxis.getInstance());
    }

}
