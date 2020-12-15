package com.dust.core.task.time;

import com.dust.core.task.Task;

public class DefaultTimeRepeatTask extends AbstractTimeRepeatTask {

    public DefaultTimeRepeatTask(Task task, long delayTime, int repeatMaxCount) {
        super(task, delayTime, repeatMaxCount);
    }

}
