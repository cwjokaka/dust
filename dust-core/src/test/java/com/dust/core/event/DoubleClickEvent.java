package com.dust.core.event;

public class DoubleClickEvent implements Event<ClickData> {

    private final ClickData clickData;

    private final EventSource eventSource;

    public DoubleClickEvent(int x, int y, EventSource source) {
        this.clickData = new ClickData(x, y);
        eventSource = source;

    }

    @Override
    public ClickData getData() {
        return clickData;
    }

    @Override
    public EventSource getEventSource() {
        return eventSource;
    }

}
