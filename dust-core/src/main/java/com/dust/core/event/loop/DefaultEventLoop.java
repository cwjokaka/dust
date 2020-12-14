package com.dust.core.event.loop;

import com.dust.core.enums.TimeEnum;
import com.dust.core.sys.FrameSystem;
import com.dust.core.task.*;
import com.dust.core.task.frame.AbstractFrameDelayTask;
import com.dust.core.task.time.AbstractTimeDelayTask;
import com.dust.core.task.time.DefaultTimeDelayTask;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop extends AbstractEventLoop {

    /**
     * 执行事件循环的线程
     */
    private final ScheduledExecutorService eventLoopThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * 基于时间的延时任务队列
     */
    private final PriorityQueue<AbstractTimeDelayTask> taskTimePriorityQueue = new PriorityQueue<>();

    /**
     * 基于帧的延时任务队列
     */
    private final PriorityQueue<AbstractFrameDelayTask> taskFramePriorityQueue = new PriorityQueue<>();

    /**
     * 基于时间的就绪任务队列
     */
    private final Queue<AbstractTimeDelayTask> taskTimeQueue = new LinkedList<>();

    @Override
    public void run() {
        eventLoopThread.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void executeLogic() {
        FrameSystem.increaseFrame();
        executeEachFrame();
    }

    protected abstract void executeEachFrame();

    @Override
    public void terminate() {
        eventLoopThread.shutdown();
    }

    public void submitTimeDelayTask(Task task, long delayTime) {
        submitTimeDelayTask(task, delayTime, TimeEnum.MILLISECOND);
    }

    public void submitTimeDelayTask(Task task, long delayTime, TimeEnum timeEnum) {
        taskTimePriorityQueue.add(new DefaultTimeDelayTask(task, delayTime * timeEnum.getOffset()));
    }

//    public void submitScheduleTask(Task task, long delayTime) {
//        taskTimePriorityQueue.add(new DefaultScheduleTask(task, delayTime));
//    }
//
//    public void submitRepeatTask(Task task, long delayTime, int repeatMaxCount) {
//        taskTimePriorityQueue.add(new DefaultRepeatTask(task, delayTime, repeatMaxCount));
//    }

//    public void submitFrameRepeatTask(Task task, long delayTime, int repeatMaxCount) {
//        taskFramePriorityQueue.add(new DefaultRepeatTask(task, delayTime, repeatMaxCount));
//    }

    @Override
    protected void runAllTasks() {
        fetchAllReadyTask();
        runAllReadyTask();
    }

    private void fetchAllReadyTask() {
        while (!taskTimePriorityQueue.isEmpty() && taskTimePriorityQueue.peek().isTimeUp()) {
            taskTimeQueue.add(taskTimePriorityQueue.poll());
        }
    }

    private void runAllReadyTask() {
        while (!taskTimeQueue.isEmpty()) {
            AbstractTimeDelayTask delayTask = taskTimeQueue.poll();
            if (delayTask.isTerminated()) {
                continue;
            }
            if (!delayTask.isExecutable()) {
                taskTimePriorityQueue.add(delayTask);
                continue;
            }
            delayTask.run();
            if (delayTask instanceof ScheduleTask) {
                ((ScheduleTask) delayTask).refreshTime();
                taskTimePriorityQueue.add(delayTask);
            }
        }
    }

}
