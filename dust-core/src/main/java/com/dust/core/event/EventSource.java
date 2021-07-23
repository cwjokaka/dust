package com.dust.core.event;

import java.util.ServiceLoader;

public interface EventSource {

    /**
     * 注册监听器
     * @param eventClass 事件类型
     * @param eventListener 注册的监听器
     */
    <E extends Event<?>> void on(Class<E> eventClass, EventListener<E> eventListener);

    /**
     * 发射事件
     * @param event 事件
     */
    void emit(Event<?> event);

    /**
     * 处理事件
     */
    <E extends Event<?>> void handle(E event);

//    /**
//     * 注册一次性监听器
//     * @param eventName 事件名称
//     * @param eventListener 注册的监听器
//     */
//    void once(String eventName, OnceEventListener eventListener);
//
//    /**
//     * 去除监听器
//     * @param eventName 事件名称
//     * @param eventListener 要去除的监听器
//     */
//    void off(String eventName, EventListener eventListener);
//
//    /**
//     * 去除所有监听
//     * @param eventName 事件名称
//     */
//    void offAll(String eventName);
//
//    /**
//     * 触发事件
//     * @param eventName 事件名称
//     * @param event 事件
//     */
//    void emit(String eventName, Event event);

    /**
     * 获取所有服务实现
     * @return 所有服务实现
     */
    static Iterable<EventSource> getEventSources() {
        return ServiceLoader.load(EventSource.class);
    }

}
