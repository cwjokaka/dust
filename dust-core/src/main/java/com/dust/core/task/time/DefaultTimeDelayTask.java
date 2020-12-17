package com.dust.core.task.time;

import com.dust.core.task.param.DelayTaskParam;

/**
 * 默认基于时间的延时任务
 */
public class DefaultTimeDelayTask extends AbstractTimeDelayTask {

    public DefaultTimeDelayTask(DelayTaskParam delayTaskParam) {
        super(
                delayTaskParam.getTask(),
                delayTaskParam.getDelay() * delayTaskParam.getTimeEnum().getOffset(),
                delayTaskParam.getDelay() * delayTaskParam.getTimeEnum().getOffset()
        );
    }

}
