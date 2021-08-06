package com.dust.actor.impl;

import com.dust.actor.Mail;
import com.dust.actor.MailBox;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultMailBox<T> implements MailBox<T> {

    private final BlockingQueue<Mail<T>> mailQueue = new LinkedBlockingQueue<>();

    @Override
    public void receive(Mail<T> message) {
        mailQueue.add(message);
    }

    @Override
    public Mail<T> take() throws InterruptedException {
        return mailQueue.take();
    }

    @Override
    public void clear() {
        mailQueue.clear();
    }

}
