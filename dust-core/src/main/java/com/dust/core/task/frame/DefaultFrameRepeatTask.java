package com.dust.core.task.frame;

import com.dust.core.task.Task;

public class DefaultFrameRepeatTask extends AbstractFrameRepeatTask {

    public DefaultFrameRepeatTask(Task task, long initLoopDelay, long loopDelay, int repeatCount) {
        super(task, initLoopDelay, loopDelay, repeatCount);
    }

    @Override
    public boolean isTimeUp() {
        return false;
    }

}
