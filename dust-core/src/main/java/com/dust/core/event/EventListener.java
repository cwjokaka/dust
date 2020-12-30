package com.dust.core.event;

/**
 * 事件监听器
 */
@FunctionalInterface
public interface EventListener {

    void listen(Event event);

    /**
     * 是否可重用
     * @return 可以
     */
    default boolean reusable() {
        return true;
    }

}
