package com.dust.core.task.scheduler;

import com.dust.core.task.DelayTask;
import com.dust.core.task.RepeatTask;
import com.dust.core.task.ScheduleTask;
import com.dust.core.task.param.DelayTaskParam;
import com.dust.core.task.param.RepeatTaskParam;
import com.dust.core.task.param.ScheduleTaskParam;

/**
 * 任务调度器
 */
public interface Scheduler {

    DelayTask submitTimeDelayTask(DelayTaskParam param);

    ScheduleTask submitTimeScheduleTask(ScheduleTaskParam param);

    RepeatTask submitTimeRepeatTask(RepeatTaskParam param);

    DelayTask submitFrameDelayTask(DelayTaskParam param);

    ScheduleTask submitFrameScheduleTask(ScheduleTaskParam param);

    RepeatTask submitFrameRepeatTask(RepeatTaskParam param);

    void runAllTasks();

}
