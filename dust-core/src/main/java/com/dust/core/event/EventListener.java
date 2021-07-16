package com.dust.core.event;

/**
 * 事件监听器
 */
@FunctionalInterface
public interface EventListener {

    /**
     * 处理发生的事件
     * @param event 事件
     */
    void handle(Event event);

    /**
     * 是否可重复监听
     * @return 可以
     */
    default boolean reusable() {
        return true;
    }

}
