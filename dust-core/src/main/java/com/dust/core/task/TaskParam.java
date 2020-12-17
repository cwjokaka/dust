package com.dust.core.task;

import com.dust.core.enums.TimeEnum;
import com.dust.core.task.param.RepeatTaskParam;

import java.util.Objects;

public class TaskParam implements RepeatTaskParam {

    private final Task task;

    private final long initDelay;

    private final long delay;

    private final int repeatCount;

    private final TimeEnum timeEnum;

    private TaskParam(Task task, long initDelay, long delay, int repeatCount, TimeEnum timeEnum) {
        this.task = task;
        this.initDelay = initDelay;
        this.delay = delay;
        this.repeatCount = repeatCount;
        this.timeEnum = timeEnum;
    }

    @Override
    public int getRepeatCount() {
        return repeatCount;
    }

    @Override
    public long getInitDelay() {
        return initDelay;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public TimeEnum getTimeEnum() {
        return timeEnum;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Task task;

        private long delay = 5;

        private long initDelay = delay;

        private int repeatCount = 1;

        private TimeEnum timeEnum = TimeEnum.MILLISECOND;

        public Builder task(Task task) {
            this.task = task;
            return this;
        }

        public Builder initDelay(long initDelay) {
            this.initDelay = initDelay;
            return this;
        }

        public Builder delay(long delay) {
            this.delay = delay;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        public Builder timeEnum(TimeEnum timeEnum) {
            this.timeEnum = timeEnum;
            return this;
        }

        public TaskParam build() {
            return new TaskParam(
                    Objects.requireNonNull(task),
                    initDelay,
                    delay,
                    repeatCount,
                    timeEnum);
        }

    }

}
