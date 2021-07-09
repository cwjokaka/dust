package com.dust.core.scheduler;

import com.dust.core.task.Task;
import com.dust.core.task.TaskRef;

public interface Scheduler {

    TaskRef setTimeDelay(Task task, long timeDelay);


    TaskRef setTimeRepeat(Task task, int repeatCount, long timeDelay);

    TaskRef setTimeRepeat(Task task, int repeatCount, long initTimeDelay, long timeDelay);


    TaskRef setTimeInterval(Task task, long timeDelay);

    TaskRef setTimeInterval(Task task, long initTimeDelay, long timeDelay);


    TaskRef setLoopDelay(Task task, long loopDelay);


    TaskRef setLoopRepeat(Task task, long loopDelay, int repeatCount);

    TaskRef setLoopRepeat(Task task, long initLoopDelay, long loopDelay, int repeatCount);


    TaskRef setLoopInterval(Task task, long loopDelay);

    TaskRef setLoopInterval(Task task, long initLoopDelay, long loopDelay);


    void runAllTasks();


}
