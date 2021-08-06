package com.dust.actor;

import com.dust.actor.impl.DefaultActorSystem;

public interface ActorSystem {

    <T> Actor<T> create(Behavior<T> behavior);

    <T> Actor<T> createAndStart(Behavior<T> behavior);

    String getName();

    void shutdown();

    static ActorSystem of(String systemName) {
        return new DefaultActorSystem(systemName);
    }

}
