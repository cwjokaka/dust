package com.dust.actor;

/**
 * @author LENOVO
 */
public interface Actor<T> extends Runnable {

    void send(Actor<T> another, T data);

    void receive(Mail<T> mail);

}
