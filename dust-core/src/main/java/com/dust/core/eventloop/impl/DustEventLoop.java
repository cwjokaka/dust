package com.dust.core.eventloop.impl;

import com.dust.core.eventloop.ScheduleEventLoop;
import com.dust.core.scheduler.Scheduler;
import com.dust.core.scheduler.impl.SchedulerImpl;
import com.dust.core.task.Task;
import com.dust.core.task.TaskRef;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DustEventLoop extends AbstractEventLoop {

    /**
     * 循环周期长度(毫秒)
     */
    private final long refreshCycle;

    /**
     * 执行事件循环的线程
     */
    private ScheduledExecutorService eventLoopThread;

    private volatile boolean isRun = false;

    /**
     * 任务调度器
     */
    private final Scheduler scheduler = new SchedulerImpl();

    public DustEventLoop(long refreshCycle) {
        this.refreshCycle = refreshCycle;
    }

    @Override
    public void run() {
        this.isRun = true;
        rebuildEventLoopThread();
    }


    @Override
    public void pause() {
        this.isRun = false;
        eventLoopThread.shutdown();
    }

    @Override
    public void resume() {
        rebuildEventLoopThread();
    }

    @Override
    public void terminate() {
        eventLoopThread.shutdown();
    }

    @Override
    public void runAllTasks() {
        scheduler.runAllTasks();
    }

    private void onLoop() {
        if (isRun) {
            beforeLoop();
            loop();
            afterLoop();
        }
    }

    /**
     * 进行主循环前的方法
     */
    protected void beforeLoop() {}

    /**
     * 进行主循环后执行的方法
     */
    protected void afterLoop() {}

    /**
     * 重新构建事件循环线程
     */
    private void rebuildEventLoopThread() {
        if (eventLoopThread != null && !eventLoopThread.isShutdown()) {
            return;
        }
        eventLoopThread = Executors.newSingleThreadScheduledExecutor();
        eventLoopThread.scheduleWithFixedDelay(
                this::onLoop,
                0,
                refreshCycle,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    protected void processAllEvents() {
        // TODO
    }

    @Override
    protected void executeEachFrame() {

    }

    @Override
    public TaskRef setTimeDelay(Task task, long timeDelay) {
        return scheduler.setTimeDelay(task, timeDelay);
    }

    @Override
    public TaskRef setTimeRepeat(Task task, int repeatCount, long timeDelay) {
        return scheduler.setTimeRepeat(task, repeatCount, timeDelay);
    }

    @Override
    public TaskRef setTimeRepeat(Task task, int repeatCount, long initTimeDelay, long timeDelay) {
        return scheduler.setTimeRepeat(task, repeatCount, initTimeDelay, timeDelay);
    }

    @Override
    public TaskRef setTimeInterval(Task task, long timeDelay) {
        return scheduler.setTimeInterval(task, timeDelay);
    }

    @Override
    public TaskRef setTimeInterval(Task task, long initTimeDelay, long timeDelay) {
        return scheduler.setTimeInterval(task, initTimeDelay, timeDelay);
    }

    @Override
    public TaskRef setLoopDelay(Task task, long loopDelay) {
        return scheduler.setLoopDelay(task, loopDelay);
    }

    @Override
    public TaskRef setLoopRepeat(Task task, long loopDelay, int repeatCount) {
        return scheduler.setLoopRepeat(task, loopDelay, repeatCount);
    }

    @Override
    public TaskRef setLoopRepeat(Task task, long initLoopDelay, long loopDelay, int repeatCount) {
        return scheduler.setLoopRepeat(task, initLoopDelay, loopDelay, repeatCount);
    }

    @Override
    public TaskRef setLoopInterval(Task task, long loopDelay) {
        return scheduler.setLoopInterval(task, loopDelay);
    }

    @Override
    public TaskRef setLoopInterval(Task task, long initLoopDelay, long loopDelay) {
        return scheduler.setLoopInterval(task, initLoopDelay, loopDelay);
    }
}
