package com.dust.core.task.tick;

import com.dust.core.task.Task;

public class DefaultTickRepeatTask extends AbstractTickRepeatTask {

    public DefaultTickRepeatTask(Task task, long initLoopDelay, long loopDelay, int repeatCount) {
        super(task, initLoopDelay, loopDelay, repeatCount);
    }

    @Override
    public boolean isTimeUp() {
        return false;
    }

}
