package com.dust.core.task.frame;

import com.dust.core.axis.FrameAxis;
import com.dust.core.task.AbstractScheduleTask;
import com.dust.core.task.Task;

public abstract class AbstractFrameScheduleTask extends AbstractScheduleTask {

    public AbstractFrameScheduleTask(Task task, long delayTime) {
        super(task, delayTime, FrameAxis.getInstance());
    }

}
