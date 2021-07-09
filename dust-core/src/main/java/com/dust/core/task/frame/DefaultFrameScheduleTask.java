package com.dust.core.task.frame;

import com.dust.core.task.Task;

public class DefaultFrameScheduleTask extends AbstractFrameScheduleTask {

    public DefaultFrameScheduleTask(Task task, long initLoopDelay, long loopDelay) {
        super(task, initLoopDelay, loopDelay);
    }

}
