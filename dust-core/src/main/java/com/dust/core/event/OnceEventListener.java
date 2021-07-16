package com.dust.core.event;

@FunctionalInterface
public interface OnceEventListener extends EventListener {

    /**
     * 是否可重用
     * @return 不可
     */
    @Override
    default boolean reusable() {
        return false;
    }

}
