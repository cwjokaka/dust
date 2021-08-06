package com.dust.actor;

/**
 * @author LENOVO
 */
public interface Behavior<T> {

    void onReceive(Actor<T> receiver, Mail<T> mail);

    void onException(Actor<T> receiver, Exception e);

}
