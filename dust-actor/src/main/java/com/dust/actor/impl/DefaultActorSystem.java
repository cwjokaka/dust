package com.dust.actor.impl;

import com.dust.actor.Actor;
import com.dust.actor.ActorSystem;
import com.dust.actor.Behavior;

import java.util.concurrent.*;

public class DefaultActorSystem implements ActorSystem {

    private final String name;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            2,
            2,
            0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            r -> new Thread(r, "ActorSystem")
    );

    public DefaultActorSystem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public <T> Actor<T> create(Behavior<T> behavior) {
        return new DefaultActor<>(behavior);
    }

    @Override
    public <T> Actor<T> createAndStart(Behavior<T> behavior) {
        Actor<T> actor = create(behavior);
        executorService.execute(actor);
        return actor;
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }

}
