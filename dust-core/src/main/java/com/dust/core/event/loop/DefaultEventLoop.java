package com.dust.core.event.loop;

import com.dust.core.task.DelayTask;
import com.dust.core.task.RepeatTask;
import com.dust.core.task.ScheduleTask;
import com.dust.core.task.param.DelayTaskParam;
import com.dust.core.task.param.RepeatTaskParam;
import com.dust.core.task.param.ScheduleTaskParam;
import com.dust.core.task.scheduler.DefaultScheduler;
import com.dust.core.task.scheduler.Scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop extends AbstractEventLoop {

    /**
     * 循环周期长度(微秒)
     */
    private final long refreshCycle;

    /**
     * 执行事件循环的线程
     */
    private ScheduledExecutorService eventLoopThread;

    /**
     * 任务调度器
     */
    private final Scheduler scheduler = new DefaultScheduler();

    public DefaultEventLoop(long refreshCycle) {
        this.refreshCycle = refreshCycle;
    }

    @Override
    public void run() {
        rebuildEventLoopThread();
    }


    @Override
    public void pause() {
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
    protected void runAllTasks() {
        scheduler.runAllTasks();
    }

    private void onLoop() {
        beforeLoop();
        loop();
        afterLoop();
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
                TimeUnit.MICROSECONDS
        );
    }

    public DelayTask submitTimeDelayTask(DelayTaskParam param) {
        return scheduler.submitTimeDelayTask(param);
    }

    public ScheduleTask submitTimeScheduleTask(ScheduleTaskParam param) {
        return scheduler.submitTimeScheduleTask(param);
    }

    public RepeatTask submitTimeRepeatTask(RepeatTaskParam param) {
        return scheduler.submitTimeRepeatTask(param);
    }

    public DelayTask submitFrameDelayTask(DelayTaskParam param) {
        return scheduler.submitFrameDelayTask(param);
    }

    public ScheduleTask submitFrameScheduleTask(ScheduleTaskParam param) {
        return scheduler.submitFrameScheduleTask(param);
    }

    public RepeatTask submitFrameRepeatTask(RepeatTaskParam param) {
        return scheduler.submitTimeRepeatTask(param);
    }

    @Override
    protected void processAllEvents() {
        // TODO
    }

}
