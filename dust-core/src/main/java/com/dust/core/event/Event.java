package com.dust.core.event;

/**
 * 事件
 */
public interface Event<T> {

    /**
     * 获取事件的关联实体
     * @return 实体
     */
    T getObject();

    /**
     * 是否能继续执行
     */
    boolean canMao();
}
