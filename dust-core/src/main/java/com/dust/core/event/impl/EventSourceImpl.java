package com.dust.core.event.impl;

import com.dust.core.event.*;
import com.dust.core.event.EventListener;
import com.dust.core.event.internal.EventQueueFactory;

import java.util.*;

/**
 * 事件源
 */
public class EventSourceImpl implements EventSource {

    /**
     * key: 事件名称
     * value: 已注册的监听器列表
     */
    private final Map<Class<? extends Event<?>>, List<EventListener<? extends Event<?>>>> listenerMap = new HashMap<>();

    private final EventQueue eventQueue;

    public EventSourceImpl(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    public static EventSourceImpl provider() {
        return new EventSourceImpl(EventQueueFactory.getMainEventQueue());
    }

    @Override
    public <E extends Event<?>> void on(Class<E> eventClass, EventListener<E> eventListener) {
        List<EventListener<? extends Event<?>>> listeners = listenerMap.get(eventClass);
        if (listeners == null) {
            listeners = new LinkedList<>();
        }
        listeners.add(eventListener);
        listenerMap.put(eventClass, listeners);
    }

    @Override
    public <E extends Event<?>> void once(Class<E> eventClass, OnceEventListener<E> eventListener) {
        on(eventClass, eventListener);
    }

    @Override
    public <E extends Event<?>> void off(Class<E> eventClass, EventListener<E> eventListener) {
        List<EventListener<? extends Event<?>>> listeners = listenerMap.get(eventClass);
        if (listeners != null) {
            listeners.remove(eventListener);
        }
    }

    @Override
    public <E extends Event<?>> void offAll(Class<E> eventClass) {
        listenerMap.remove(eventClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Event<?>> void handle(E event) {
        List<EventListener<? extends Event<?>>> listeners = listenerMap.get(getEventRealClass(event));
        if (listeners == null) {
            return;
        }
        Iterator<EventListener<? extends Event<?>>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            EventListener listener = iterator.next();
            listener.handle(event);
            if (!listener.reusable()) {
                iterator.remove();
            }
        }
    }

    private Class<?> getEventRealClass(Event<?> event) {
        Class<? extends Event> eventClass = event.getClass();
        if (eventClass.isAnonymousClass()) {
            Class<?> superclass = eventClass.getSuperclass();
            if (superclass == Object.class) {
                return eventClass.getInterfaces()[0];
            }
            return superclass;
        }
        return eventClass;
    }


    @Override
    public void emit(Event<?> event) {
        eventQueue.offer(event);
    }


//
//    /**
//     * 卸载事件监听
//     * @param eventName 事件名称
//     * @param eventListener 监听器
//     */
//    @Override
//    public void off(String eventName, EventListener eventListener) {
//        List<EventListener> listeners = listenerMap.get(eventName);
//        if (listeners == null) {
//            return;
//        }
//        listeners.remove(eventListener);
//    }
//
//    /**
//     * 卸载所有事件监听
//     * @param eventName 事件名称
//     */
//    @Override
//    public void offAll(String eventName) {
//        if (!listenerMap.containsKey(eventName)) {
//            return;
//        }
//        listenerMap.remove(eventName);
//    }
//
//    /**
//     * 触发事件
//     * @param eventName 事件名称
//     */
//    @Override
//    public void emit(String eventName, Event event) {
////        event.getClass().
//        eventQueue.offer(event);
//        List<EventListener> listeners = listenerMap.get(eventName);
//        if (listeners == null) {
//            return;
//        }
//
//        Iterator<EventListener> iterator = listeners.iterator();
//        while (iterator.hasNext()) {
//            EventListener listener = iterator.next();
//            listener.handle(event);
//            if (!listener.reusable()) {
//                iterator.remove();
//            }
//        }
//    }

}
