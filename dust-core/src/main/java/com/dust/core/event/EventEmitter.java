package com.dust.core.event;

import java.util.*;

/**
 * 事件发射器
 */
public class EventEmitter {

    /**
     * key: 事件名称
     * value: 已注册的监听器列表
     */
    private final Map<String, List<EventListener>> listenerMap = new HashMap<>();

    /**
     * 注册事件监听器
     * @param eventName 事件名称
     * @param eventListener 监听器
     */
    public void on(String eventName, EventListener eventListener) {
        List<EventListener> listeners = listenerMap.get(eventName);
        if (listeners == null) {
            listeners = new LinkedList<>();
        }
        listeners.add(eventListener);
        listenerMap.put(eventName, listeners);
    }

    /**
     * 注册一次性事件监听
     * @param eventName 事件名称
     * @param eventListener 监听器
     */
    public void once(String eventName, OnceEventListener eventListener) {
        on(eventName, eventListener);
    }

    /**
     * 卸载事件监听
     * @param eventName 事件名称
     * @param eventListener 监听器
     */
    public void off(String eventName, EventListener eventListener) {
        List<EventListener> listeners = listenerMap.get(eventName);
        if (listeners == null) {
            return;
        }
        listeners.remove(eventListener);
    }

    /**
     * 卸载所有事件监听
     * @param eventName 事件名称
     */
    public void offAll(String eventName) {
        if (!listenerMap.containsKey(eventName)) {
            return;
        }
        listenerMap.remove(eventName);
    }

    /**
     * 触发事件
     * @param eventName 事件名称
     */
    public void emit(String eventName, Event event) {
        List<EventListener> listeners = listenerMap.get(eventName);
        if (listeners == null) {
            return;
        }
        Iterator<EventListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            EventListener listener = iterator.next();
            listener.listen(event);
            if (!listener.reusable()) {
                iterator.remove();
            }
        }
    }

}
