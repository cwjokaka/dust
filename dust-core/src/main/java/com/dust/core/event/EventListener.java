package com.dust.core.event;

/**
 * 事件监听器
 */
@FunctionalInterface
public interface EventListener<T> {

    /**
     * 处理发生的事件
     * @param event 事件
     */
    void handle(Event<T> event);

    /**
     * 是否可重用
     * @return 可以
     */
    default boolean reusable() {
        return true;
    }

}
