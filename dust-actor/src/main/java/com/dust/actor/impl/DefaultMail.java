package com.dust.actor.impl;

import com.dust.actor.Actor;
import com.dust.actor.Mail;

import java.util.Objects;

public class DefaultMail<T> implements Mail<T> {

    private Actor<T> source;

    private T data;

    public DefaultMail(Actor<T> source, T data) {
        this.source = Objects.requireNonNull(source);
        this.data = Objects.requireNonNull(data);
    }

    @Override
    public Actor<T> getSender() {
        return source;
    }

    @Override
    public T getData() {
        return data;
    }

}
