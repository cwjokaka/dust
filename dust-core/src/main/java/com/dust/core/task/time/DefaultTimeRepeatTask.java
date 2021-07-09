package com.dust.core.task.time;

import com.dust.core.task.Task;

public class DefaultTimeRepeatTask extends AbstractTimeRepeatTask {

    public DefaultTimeRepeatTask(Task task, int repeatCount, long initDelay, long timeDelay) {
        super(
                task,
                initDelay,
                timeDelay,
                repeatCount
        );
    }

}
