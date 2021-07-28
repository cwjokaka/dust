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
        eventLoopDoNothing.start();
    }

    @After
    public void after() {
        eventLoopDoNothing.terminate();
    }

    @Test
    public void testAbsEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger clickCounter = new AtomicInteger(0);
        eventSource.on(ClickEvent.class, event -> {
            clickCounter.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        });
        emitClickEvent(eventSource);
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        Assert.assertEquals(2, clickCounter.get());
    }

    private void emitClickEvent(EventSource eventSource) {
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
    }

    @Test
    public void testConEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger clickCounter = new AtomicInteger(0);
        eventSource.on(DoubleClickEvent.class, event -> {
            clickCounter.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        });
        eventSource.emit(new DoubleClickEvent(1, 2, eventSource));
        Thread.sleep(1000);
        Assert.assertEquals(1, clickCounter.get());
    }

    @Test
    public void testInterfaceEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
//        EventSource eventSource = EventSource.getEventSources().iterator().next();
        AtomicInteger clickCounter = new AtomicInteger(0);
        eventSource.on(NoClickEvent.class, event -> {
            clickCounter.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        });
        eventSource.emit(new NoClickEvent() {
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
        Assert.assertEquals(1, clickCounter.get());
    }

    @Test
    public void testAbsOnceEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger clickCounter = new AtomicInteger(0);
        eventSource.once(ClickEvent.class, event -> {
            clickCounter.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        });
        // 第一次触发事件
        emitClickEvent(eventSource);
        // 第二次触发事件(已无监听器)
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        Assert.assertEquals(1, clickCounter.get());
    }

    @Test
    public void testOffAllEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger clickCounter = new AtomicInteger(0);
        eventSource.on(ClickEvent.class, event -> {
            clickCounter.getAndIncrement();
        });
        eventSource.on(ClickEvent.class, event -> {
            clickCounter.getAndIncrement();
        });
        // 第一次触发事件
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        eventSource.offAll(ClickEvent.class);
        // 第二次触发事件(已无监听器)
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        Assert.assertEquals(2, clickCounter.get());
    }

    @Test
    public void testOffEventBundle() throws InterruptedException {
        EventSource eventSource = new EventSourceImpl(eventLoopDoNothing);
        AtomicInteger clickCounter = new AtomicInteger(0);
        EventListener<ClickEvent> eventListener = event -> {
            clickCounter.getAndIncrement();
            Assert.assertEquals(1, event.getData().getX());
            Assert.assertEquals(2, event.getData().getY());
        };
        eventSource.on(ClickEvent.class, eventListener);
        // 第一次触发事件
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        eventSource.off(ClickEvent.class, eventListener);
        // 第二次触发事件(已无监听器)
        emitClickEvent(eventSource);
        Thread.sleep(1000);
        Assert.assertEquals(1, clickCounter.get());
    }

}
