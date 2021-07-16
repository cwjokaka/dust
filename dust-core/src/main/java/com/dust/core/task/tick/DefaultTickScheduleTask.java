package com.dust.core.task.tick;

import com.dust.core.task.Task;

public class DefaultTickScheduleTask extends AbstractTickScheduleTask {

    public DefaultTickScheduleTask(Task task, long initLoopDelay, long loopDelay) {
        super(task, initLoopDelay, loopDelay);
    }

}
