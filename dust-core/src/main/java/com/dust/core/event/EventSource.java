package com.dust.core.event;

import java.util.ServiceLoader;

/**
 * 事件源
 */
public interface EventSource {

    /**
     * 注册监听器
     * @param eventClass 事件类型
     * @param eventListener 注册的监听器
     */
    <E extends Event<?>> void on(Class<E> eventClass, EventListener<E> eventListener);


    /**
     * 注册一次性监听器
     * @param eventClass 事件类型
     * @param eventListener 注册的监听器
     */
    <E extends Event<?>> void once(Class<E> eventClass, OnceEventListener<E> eventListener);


    /**
     * 发射事件
     * @param event 事件
     */
    void emit(Event<?> event);

    /**
     * 处理事件
     */
    <E extends Event<?>> void handle(E event);

    /**
     * 去除监听器
     * @param eventClass 事件
     * @param eventListener 要去除的监听器
     */
    <E extends Event<?>> void off(Class<E> eventClass, EventListener<E> eventListener);

    /**
     * 去除所有监听
     * @param eventClass 事件
     */
    <E extends Event<?>> void offAll(Class<E> eventClass);

    /**
     * 获取所有服务实现
     * @return 所有服务实现
     */
    static Iterable<EventSource> getEventSources() {
        return ServiceLoader.load(EventSource.class);
    }

}
