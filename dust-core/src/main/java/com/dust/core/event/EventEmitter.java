package com.dust.core.event;

import java.util.ServiceLoader;

public interface EventEmitter {

    <T> void on(String eventName, EventListener<T> eventListener);

    <T> void once(String eventName, OnceEventListener<T> eventListener);

    <T> void off(String eventName, EventListener<T> eventListener);

    <T> void offAll(String eventName);

    <T> void emit(String eventName, Event<T> event);

    static Iterable<EventEmitter> getEventEmitters() {
        return ServiceLoader.load(EventEmitter.class);
    }

}
