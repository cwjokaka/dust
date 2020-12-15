package com.dust.core.task.frame;

import com.dust.core.axis.Axis;
import com.dust.core.axis.FrameAxis;
import com.dust.core.task.AbstractRepeatTask;
import com.dust.core.task.Task;

public abstract class AbstractFrameRepeatTask extends AbstractRepeatTask {

    public AbstractFrameRepeatTask(Task task, long delayTime, int repeatMaxCount) {
        super(task, delayTime, repeatMaxCount, FrameAxis.getInstance());
    }

}
