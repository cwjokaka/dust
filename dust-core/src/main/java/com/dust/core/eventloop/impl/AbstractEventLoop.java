package com.dust.core.eventloop.impl;

import com.dust.core.eventloop.ScheduleEventLoop;
import com.dust.core.sys.TickSystem;

/**
 * 事件循环模板
 */
public abstract class AbstractEventLoop implements ScheduleEventLoop {

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
        TickSystem.increaseTick();
        executeEachTick();
    }

    /**
     * 执行每帧的逻辑
     */
    protected abstract void executeEachTick();

    /**
     * 处理所有就绪的事件
     */
    protected abstract void processAllEvents();

}
