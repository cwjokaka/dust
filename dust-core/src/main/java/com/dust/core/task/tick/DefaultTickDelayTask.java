package com.dust.core.task.tick;

import com.dust.core.task.Task;

public class DefaultTickDelayTask extends AbstractTickDelayTask {

    public DefaultTickDelayTask(Task task, long loopDelay) {
        super(task, loopDelay, loopDelay);
    }

}
