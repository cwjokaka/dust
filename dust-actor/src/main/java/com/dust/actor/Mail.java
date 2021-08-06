package com.dust.actor;

public interface Mail<T> {

    Actor<T> getSender();

    T getData();

}
