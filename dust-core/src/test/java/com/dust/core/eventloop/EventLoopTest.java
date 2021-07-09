package com.dust.core.eventloop;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class EventLoopTest {

    @Test
    public void testEventLoop() throws InterruptedException {
        EventLoop myEventLoop = new SayHiEventLoop(500);
        myEventLoop.run();
        System.out.println("run");
        Thread.sleep(2000);
        System.out.println("pause");
        myEventLoop.pause();
        Thread.sleep(2000);
        System.out.println("resume");
        myEventLoop.resume();
    }

    @Test
    public void testTimeDelay() throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        ScheduleEventLoop myEventLoop = new TimeDelayEventLoop(33);
        myEventLoop.run();
        myEventLoop.setTimeDelay(
                () -> num.set(1),
                500);
        Assert.assertEquals(0, num.get());
        Thread.sleep(1000);
        Assert.assertEquals(1, num.get());
    }

    @Test
    public void testTimeRepeat() throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        ScheduleEventLoop myEventLoop = new TimeDelayEventLoop(1);
        myEventLoop.run();
        myEventLoop.setTimeRepeat(
                () -> num.addAndGet(1),
                3,
                100);
        Assert.assertEquals(0, num.get());
        Thread.sleep(110);
        Assert.assertEquals(1, num.get());
        Thread.sleep(110);
        Assert.assertEquals(2, num.get());
        Thread.sleep(110);
        Assert.assertEquals(3, num.get());
        Thread.sleep(110);
        Assert.assertNotEquals(4, num.get());
    }

    @Test
    public void testTimeInterval() throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        ScheduleEventLoop myEventLoop = new TimeDelayEventLoop(1);
        myEventLoop.run();
        myEventLoop.setTimeInterval(
                () -> num.addAndGet(1),
                100);
        Assert.assertEquals(0, num.get());
        Thread.sleep(110);
        Assert.assertEquals(1, num.get());
        Thread.sleep(110);
        Assert.assertEquals(2, num.get());
        Thread.sleep(110);
        Assert.assertEquals(3, num.get());
        myEventLoop.terminate();
        Thread.sleep(110);
        Assert.assertNotEquals(4, num.get());
    }

}
