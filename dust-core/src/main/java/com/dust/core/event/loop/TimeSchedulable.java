package com.dust.core.event.loop;

import com.dust.core.enums.TimeEnum;
import com.dust.core.task.Task;

public interface TimeSchedulable {

    void submitTimeDelayTask(Task task, long delayTime);

    void submitTimeDelayTask(Task task, long delayTime, TimeEnum timeEnum);

    void submitTimeScheduleTask(Task task, long delayTime);


}