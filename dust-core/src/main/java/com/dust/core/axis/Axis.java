package com.dust.core.axis;

/**
 * 时间轴
 */
public interface Axis {

    /**
     * 刷新时间
     * @param delay 时间大小
     * @return 新时间
     */
    long refreshTime(long delay);

    /**
     * 判断是否已到执行时间
     * @param current 时间点
     * @return 布尔
     */
    boolean isTimeUp(long current);

}
