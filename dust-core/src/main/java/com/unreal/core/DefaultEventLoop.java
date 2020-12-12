package com.unreal.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认事件循环
 */
public abstract class DefaultEventLoop implements EventLoop {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public void run() {
        scheduledExecutorService.scheduleWithFixedDelay(this::loop, 0, 16, TimeUnit.MILLISECONDS);
    }

    public void loop() {
        executeLogic();
        runAllTasks();
        render();
    }

    protected abstract void executeLogic();

    protected abstract void render();

    protected abstract void runAllTasks();

}
