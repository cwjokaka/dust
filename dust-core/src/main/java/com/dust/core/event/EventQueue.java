package com.dust.core.event;

public interface EventQueue {

    void offer(Event<?> event);

    Event<?> poll();

}
