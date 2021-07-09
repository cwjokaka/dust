package com.dust.core.task.time;

import com.dust.core.task.Task;

/**
 * 默认基于时间的延时任务
 */
public class DefaultTimeDelayTask extends AbstractTimeDelayTask {

    public DefaultTimeDelayTask(Task task, long timeDelay) {
        super(
                task,
                timeDelay,
                timeDelay
        );
    }

}
