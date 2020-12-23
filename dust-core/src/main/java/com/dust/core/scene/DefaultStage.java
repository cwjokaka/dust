package com.dust.core.scene;

import java.util.*;

/**
 * 舞台
 */
public class DefaultStage implements SceneManager {

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

    public DefaultStage(int fps) {
        this.sceneEventLoop = new SceneEventLoop(calRefreshCycle(fps));
    }

    private long calRefreshCycle(int fps) {
        return 1000_000 / fps;
    }

    @Override
    public void pushScene(Scene scene) {
        if (sceneMap.containsKey(scene.getName())) {
            return;
        }
        pauseCurrentEventLoop();
        sceneMap.put(scene.getName(), scene);
        sceneEventLoop.switchTo(scene);
        sceneStack.push(scene);
        runCurrentScene();
    }

    @Override
    public Scene popScene() {
        if (sceneStack.isEmpty()) {
            return null;
        }
        pauseCurrentEventLoop();
        Scene pop = sceneStack.pop();
        sceneMap.remove(pop.getName());
        sceneEventLoop.switchTo(currentScene());
        resumeCurrentScene();
        return pop;
    }

    @Override
    public void navigateTo(String sceneName) {
        if (!sceneMap.containsKey(sceneName)) {
            return;
        }
        pauseCurrentEventLoop();
        Scene top = sceneMap.get(sceneName);
        sceneStack.remove(top);
        sceneStack.push(top);
        sceneEventLoop.switchTo(currentScene());
        resumeCurrentScene();
    }

    private void pauseCurrentEventLoop() {
        sceneEventLoop.pause();
    }

    private Scene currentScene() {
        return sceneStack.peekLast();
    }

    private void runCurrentScene() {
        sceneEventLoop.run();
    }

    private void resumeCurrentScene() {
        sceneEventLoop.resume();
    }


}
