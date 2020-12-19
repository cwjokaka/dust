package com.dust.core.event.loop;

import com.dust.core.event.EventLoop;
import com.dust.core.sys.FrameSystem;

/**
 * 事件循环模板
 */
public abstract class AbstractEventLoop implements EventLoop {

    private boolean isRunning = true;
    private boolean isTerminate = false;

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
    private void executeLogic() {
        FrameSystem.increaseFrame();
        executeEachFrame();
    }

    /**
     * 执行每帧的逻辑
     */
    protected abstract void executeEachFrame();

    /**
     * 渲染画面
     */
    protected abstract void render();

    /**
     * 执行所有就绪的任务
     */
    protected abstract void runAllTasks();

    @Override
    public void pause() {
        this.isRunning = false;
    }

    @Override
    public void resume() {
        this.isRunning = true;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isTerminated() {
        return isTerminate;
    }
}
