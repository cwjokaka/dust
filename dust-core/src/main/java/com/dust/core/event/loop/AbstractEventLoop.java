package com.dust.core.event.loop;

/**
 * 事件循环模板
 */
public abstract class AbstractEventLoop implements EventLoop {

    @Override
    public void loop() {
        executeLogic();
        runAllTasks();
        render();
    }

    protected abstract void executeLogic();

    protected abstract void render();

    protected abstract void runAllTasks();


}
