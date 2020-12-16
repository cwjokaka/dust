package com.dust.core.task.time;

import com.dust.core.task.AbstractDelayTask;
import com.dust.core.task.Task;
import com.dust.core.axis.TimeAxis;

/**
 * 基于时间的延时任务
 */
public class AbstractTimeDelayTask extends AbstractDelayTask {

    public AbstractTimeDelayTask(Task task, long initDelayTime, long delayTime) {
        super(task, initDelayTime, delayTime, TimeAxis.getInstance());
    }

}
