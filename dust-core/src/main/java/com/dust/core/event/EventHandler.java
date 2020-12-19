package com.dust.core.event;

@FunctionalInterface
public interface EventHandler {

    void handle(Event event);

}
