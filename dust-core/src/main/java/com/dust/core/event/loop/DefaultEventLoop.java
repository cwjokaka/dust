package com.dust.core.event.loop;

import com.dust.core.manager.FrameManager;
import com.dust.core.task.DefaultDelayTask;
import com.dust.core.task.DelayTask;
import com.dust.core.task.Task;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop extends AbstractEventLoop {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private final PriorityQueue<DelayTask> taskPriorityQueue = new PriorityQueue<>();

    public void run() {
        scheduledExecutorService.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void executeLogic() {
        FrameManager.increaseFrame();
        execute();
    }

    protected abstract void execute();

    @Override
    public void stop() {
        scheduledExecutorService.shutdown();
    }

    public void addDelayTask(Task task, long delay) {
        taskPriorityQueue.add(new DefaultDelayTask(task, delay));
    }

    @Override
    protected void runAllTasks() {
        while (taskPriorityQueue.peek() != null && taskPriorityQueue.peek().isTimeUp()) {
            taskPriorityQueue.poll().run();
        }
    }

}
