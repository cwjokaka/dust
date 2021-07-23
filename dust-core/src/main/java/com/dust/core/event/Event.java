package com.dust.core.event;

/**
 * 事件
 */
public interface Event<D> {

    /**
     * 获取事件的关联实体
     * @return 实体
     */
    D getData();

    EventSource getEventSource();

}
