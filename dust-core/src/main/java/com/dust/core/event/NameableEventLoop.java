package com.dust.core.event;

/**
 * 命名的事件循环
 */
public interface NameableEventLoop extends EventLoop {

    /**
     * 设置名称
     * @param name 名称
     */
    void setName(String name);

    /**
     * 获取名称
     * @return 名称
     */
    String getName();

}
