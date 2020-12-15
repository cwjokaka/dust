package com.dust.core.task;

public interface RepeatTask extends ScheduleTask {

    void increaseRepeatCount();

    boolean isRepeatFinish();

}
