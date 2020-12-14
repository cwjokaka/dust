package com.dust.core.task.time;

import com.dust.core.task.Task;

public class DefaultTimeDelayTask extends AbstractTimeDelayTask {

    public DefaultTimeDelayTask(Task task, long delayTime) {
        super(task, delayTime);
    }

}
