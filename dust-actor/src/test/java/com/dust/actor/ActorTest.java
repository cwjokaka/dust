package com.dust.actor;

import org.junit.Test;

public class ActorTest {

    @Test
    public void testActor() throws InterruptedException {
        ActorSystem testSystem = ActorSystem.of("testSystem");
        Actor<Integer> actorOne = testSystem.createAndStart(new Behavior<>() {
            @Override
            public void onReceive(Actor<Integer> receiver, Mail<Integer> mail) {
                System.out.println("I, actorOne, got an int: " + mail.getData() + " from " + mail.getSender());
            }

            @Override
            public void onException(Actor<Integer> receiver, Exception e) {
                System.out.println("Something wrong!" + e.getMessage());
            }
        });
        Actor<Integer> actorTwo = testSystem.createAndStart(new Behavior<>() {
            @Override
            public void onReceive(Actor<Integer> receiver, Mail<Integer> mail) {
                System.out.println("I, actorTwo, got an int: " + mail.getData() + " from " + mail.getSender());
                if (mail.getData() == 0) {
                    throw new RuntimeException("Can not deal the num of zero");
                }
            }

            @Override
            public void onException(Actor<Integer> receiver, Exception e) {
                System.out.println("Something wrong!" + e.getMessage());
            }
        });
        actorOne.send(actorOne, 1);
        actorOne.send(actorOne, 2);
        actorTwo.send(actorOne, 567);
        actorOne.send(actorTwo, 765);
        actorOne.send(actorTwo, 0);
        Thread.sleep(2000);

    }

}
