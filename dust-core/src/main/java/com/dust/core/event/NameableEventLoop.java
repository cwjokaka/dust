package com.dust.core.event;

public interface NameableEventLoop extends EventLoop {


    void setName(String name);

    String getName();

}
