package com.dust.core.event;

import com.dust.core.event.impl.EventSourceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class EventSourceTest {

    private EventLoopDoNothing eventLoopDoNothing;

    @Before
    public void before() {
        eventLoopDoNothing = new EventLoopDoNothing(200);
        eventLoopDoNothing.run();
    }

    @After
    public void after() {
        eventLoopDoNothing.terminate();
    }

    @Test
    public void testEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger num = new AtomicInteger(0);
        eventSource.on(ClickEvent.class, event -> {
            num.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        });
        eventSource.emit(new ClickEvent() {
            @Override
            public ClickData getData() {
                return new ClickData(1, 2);
            }

            @Override
            public EventSource getEventSource() {
                return eventSource;
            }
        });
        Thread.sleep(1000);
        Assert.assertEquals(1, num.get());
    }


}
