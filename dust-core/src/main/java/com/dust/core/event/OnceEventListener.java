package com.dust.core.event;

@FunctionalInterface
public interface OnceEventListener<T> extends EventListener<T> {

    /**
     * 是否可重用
     * @return 不可以
     */
    @Override
    default boolean reusable() {
        return false;
    }

}
