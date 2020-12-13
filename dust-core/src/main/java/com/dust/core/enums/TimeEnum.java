package com.dust.core.enums;

/**
 * 时间单位
 */
public enum TimeEnum {

    MILLISECOND(1),
    SECOND(1000),
    MINUTE(1000 * 60),
    HOUR(1000 * 60 * 60),
    FRAME(0)
    ;

    long offset;

    TimeEnum(long offset) {
        this.offset = offset;
    }

    public long getOffset() {
        return offset;
    }

}
