package com.dust.core.event.internal;

import com.dust.core.annotation.MainEventQueue;
import com.dust.core.event.EventQueue;

import java.util.ServiceLoader;

public class EventQueueFactory {

    private static EventQueue mainEventQueue;

    public static EventQueue getMainEventQueue() {
        if (mainEventQueue == null) {
            mainEventQueue = ServiceLoader.load(EventQueue.class).stream()
                    .filter(eventQueueProvider -> eventQueueProvider.type().isAnnotationPresent(MainEventQueue.class))
                    .map(ServiceLoader.Provider::get)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find any EventQueue marked as @MainEventQueue in module!"));
        }
        return mainEventQueue;
    }

}
