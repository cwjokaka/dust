package com.dust.core.task.time;

import com.dust.core.axis.TimeAxis;
import com.dust.core.task.AbstractRepeatTask;
import com.dust.core.task.Task;

/**
 * 基于时间的重复定时任务
 */
public abstract class AbstractTimeRepeatTask extends AbstractRepeatTask {

    public AbstractTimeRepeatTask(Task task, long initDelayTime, long delayTime, int repeatMaxCount) {
        super(task, initDelayTime, delayTime, repeatMaxCount, TimeAxis.getInstance());
    }

}
