package com.dust.actor.impl;

import com.dust.actor.Actor;
import com.dust.actor.Behavior;
import com.dust.actor.Mail;
import com.dust.actor.MailBox;

public class DefaultActor<T> implements Actor<T> {

    private final MailBox<T> mailBox = new DefaultMailBox<>();

    private final Behavior<T> behavior;

    public DefaultActor(Behavior<T> behavior) {
        this.behavior = behavior;
    }

    @Override
    public void send(Actor<T> another, T data) {
        DefaultMail<T> mail = new DefaultMail<>(this, data);
        another.receive(mail);
    }

    @Override
    public void receive(Mail<T> mail) {
        mailBox.receive(mail);
    }


    @Override
    public void run() {
        try {
            while (true) {
                behavior.onReceive(this, mailBox.take());
            }
        } catch (Exception e) {
            behavior.onException(this, e);
        }
    }

}
