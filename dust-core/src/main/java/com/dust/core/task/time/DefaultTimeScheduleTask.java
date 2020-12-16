package com.dust.core.task.time;

import com.dust.core.task.Task;

public class DefaultTimeScheduleTask extends AbstractTimeScheduleTask {

    public DefaultTimeScheduleTask(Task task, long initDelayTime, long delayTime) {
        super(task, initDelayTime, delayTime);
    }

}
