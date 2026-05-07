package com.wildlife.model.engine;

import com.wildlife.model.entity.Animal;
import com.wildlife.model.entity.Entity;
import com.wildlife.model.environment.GameMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements Runnable {
    private GameMap map;
    private boolean running = false;
    private int tickRate = 50; // ms per tick

    public SimulationEngine(GameMap map) {
        this.map = map;
    }

    public void start() {
        if (!running) {
            running = true;
            new Thread(this).start();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();

            updateEntities();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = tickRate - elapsedTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateEntities() {
        List<Entity> deadEntities = new ArrayList<>();
        
        for (Entity entity : map.getEntities()) {
            if (entity.isAlive()) {
                if (entity instanceof Animal) {
                    ((Animal) entity).determineAction(map);
                }
                entity.update();
            } else {
                deadEntities.add(entity);
            }
        }

        for (Entity e : deadEntities) {
            map.removeEntity(e);
        }
    }
}
