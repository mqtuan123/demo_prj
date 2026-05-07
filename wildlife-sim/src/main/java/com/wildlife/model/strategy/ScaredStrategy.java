package com.wildlife.model.strategy;

import com.wildlife.model.entity.Animal;
import com.wildlife.model.entity.Entity;
import com.wildlife.model.entity.Tiger;
import com.wildlife.model.entity.Wolf;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.Location;

public class ScaredStrategy implements SurvivalStrategy {
    private SurvivalStrategy baseStrategy;

    public ScaredStrategy(SurvivalStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }

    @Override
    public void determineMove(Animal self, GameMap map) {
        Entity predator = null;
        double minDistance = Double.MAX_VALUE;

        for (Entity e : map.getEntities()) {
            if ((e instanceof Wolf || e instanceof Tiger) && e.isAlive()) {
                double dist = self.getLocation().distanceTo(e.getLocation());
                if (dist < minDistance && dist <= 150) { // View radius
                    minDistance = dist;
                    predator = e;
                }
            }
        }

        if (predator != null) {
            // Run away
            double dx = self.getLocation().getX() - predator.getLocation().getX();
            double dy = self.getLocation().getY() - predator.getLocation().getY();
            
            // Normalize and scale to move away
            double dist = Math.sqrt(dx * dx + dy * dy);
            if (dist > 0) {
                double targetX = self.getLocation().getX() + (dx / dist) * 10;
                double targetY = self.getLocation().getY() + (dy / dist) * 10;
                
                // Keep within bounds
                targetX = Math.max(0, Math.min(map.getWidth() - 1, targetX));
                targetY = Math.max(0, Math.min(map.getHeight() - 1, targetY));
                
                self.moveTowards(new Location(targetX, targetY), 1.5); // Run fast
            }
        } else {
            // No predator, use base strategy
            if (baseStrategy != null) {
                baseStrategy.determineMove(self, map);
            }
        }
    }
}
