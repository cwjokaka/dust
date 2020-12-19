package com.dust.core.event;

@FunctionalInterface
public interface EventHandler<T extends Event> {

    void handle(T event);

}
