package com.dust.core.event;

/**
 * 事件监听器
 */
@FunctionalInterface
public interface EventListener<T> {

    void listen(Event<T> event);

    /**
     * 是否可重用
     * @return 可以
     */
    default boolean reusable() {
        return true;
    }

}
