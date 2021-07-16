package com.dust.core.event;

import java.util.ServiceLoader;

public interface EventEmitter {

    /**
     * 注册监听器
     * @param eventName 事件名称
     * @param eventListener 注册的监听器
     */
    void on(String eventName, EventListener eventListener);

    /**
     * 注册一次性监听器
     * @param eventName 事件名称
     * @param eventListener 注册的监听器
     */
    void once(String eventName, OnceEventListener eventListener);

    /**
     * 去除监听器
     * @param eventName 事件名称
     * @param eventListener 要去除的监听器
     */
    void off(String eventName, EventListener eventListener);

    /**
     * 去除所有监听
     * @param eventName 事件名称
     */
    void offAll(String eventName);

    /**
     * 触发事件
     * @param eventName 事件名称
     * @param event 事件
     */
    void emit(String eventName, Event event);

    /**
     * 获取所有服务实现
     * @return 所有服务实现
     */
    static Iterable<EventEmitter> getEventEmitters() {
        return ServiceLoader.load(EventEmitter.class);
    }

}
