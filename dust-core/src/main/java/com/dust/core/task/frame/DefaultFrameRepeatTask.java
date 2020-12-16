package com.dust.core.task.frame;

import com.dust.core.task.Task;

public class DefaultFrameRepeatTask extends AbstractFrameRepeatTask {

    public DefaultFrameRepeatTask(Task task, long initDelayFrame, long delayFrame, int repeatMaxCount) {
        super(task, initDelayFrame, delayFrame, repeatMaxCount);
    }

    @Override
    public boolean isTimeUp() {
        return false;
    }

}
