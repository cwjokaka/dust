package com.dust.core.event;

/**
 * 事件监听器
 */
@FunctionalInterface
public interface EventListener {

    void listen(Event<?> event);

}
