package com.dust.core.event.loop;

import com.dust.core.event.EventLoop;
import com.dust.core.sys.FrameSystem;

/**
 * 事件循环模板
 */
public abstract class AbstractEventLoop implements EventLoop {

    /**
     * 单次主循环的内容
     */
    protected void loop() {
        executeLogic();
        runAllTasks();
        processAllEvents();
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
     * 执行所有就绪的任务
     */
    protected abstract void runAllTasks();

    /**
     * 处理所有就绪的事件
     */
    protected abstract void processAllEvents();

}
