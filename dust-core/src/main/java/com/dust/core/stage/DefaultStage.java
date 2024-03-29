package com.dust.core.stage;

import com.dust.core.scene.Scene;
import com.dust.core.scene.SceneEventLoop;

import java.util.*;

/**
 * 舞台
 */
public class DefaultStage implements Stage {

    /**
     * 事件循环
     */
    private final SceneEventLoop sceneEventLoop;

    /**
     * 场景栈
     */
    private final Deque<Scene> sceneStack = new LinkedList<>();

    /**
     * 场景Map
     */
    private final Map<String, Scene> sceneMap = new HashMap<>();

    /**
     * 1秒对应的微秒数
     */
    private static final long SECOND_TO_MICROSECOND = 1000_000;

    public DefaultStage(int fps) {
        this.sceneEventLoop = new SceneEventLoop(calRefreshCycle(fps));
    }

    private long calRefreshCycle(int fps) {
        return SECOND_TO_MICROSECOND / fps;
    }

    @Override
    public void start() {
        sceneEventLoop.start();
    }

    @Override
    public void pushScene(Scene scene) {
        if (sceneMap.containsKey(scene.getName())) {
            return;
        }
        sceneMap.put(scene.getName(), scene);
        sceneEventLoop.switchTo(scene);
        sceneEventLoop.start();
        sceneStack.push(scene);
    }

    @Override
    public Scene popScene() {
        if (sceneStack.isEmpty()) {
            return null;
        }
        Scene pop = sceneStack.pop();
        sceneMap.remove(pop.getName());
        sceneEventLoop.switchTo(currentScene());
        return pop;
    }

    @Override
    public void navigateTo(String sceneName) {
        if (!sceneMap.containsKey(sceneName)) {
            return;
        }
        Scene top = sceneMap.get(sceneName);
        sceneStack.remove(top);
        sceneStack.push(top);
        sceneEventLoop.switchTo(currentScene());
    }

    private Scene currentScene() {
        return sceneStack.peekFirst();
    }


}
