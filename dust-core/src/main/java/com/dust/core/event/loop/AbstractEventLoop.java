package com.dust.core.event.loop;

/**
 * 事件循环模板
 */
public abstract class AbstractEventLoop implements EventLoop {

    protected boolean isStop = false;

    @Override
    public void loop() {
        if (isRunning()) {
            executeLogic();
            runAllTasks();
            render();
        }
    }

    /**
     * 执行逻辑
     */
    protected abstract void executeLogic();

    /**
     * 渲染画面
     */
    protected abstract void render();

    /**
     * 执行所有就绪的任务
     */
    protected abstract void runAllTasks();

    public void stop() {
        this.isStop = true;
    }

    public void resume() {
        this.isStop = false;
    }

    public boolean isRunning() {
        return !this.isStop;
    }

}
