package com.wildlife.model.strategy;

import com.wildlife.model.entity.Animal;
import com.wildlife.model.entity.Entity;
import com.wildlife.model.entity.Plant;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.Location;

public class PassiveStrategy implements SurvivalStrategy {
    private Location targetWander = null;

    @Override
    public void determineMove(Animal self, GameMap map) {
        if (self.getHunger() < 50) {
            // Find nearest plant
            Entity nearestPlant = null;
            double minDistance = Double.MAX_VALUE;
            for (Entity e : map.getEntities()) {
                if (e instanceof Plant && e.isAlive()) {
                    double dist = self.getLocation().distanceTo(e.getLocation());
                    if (dist < minDistance && dist <= self.getHunger() * 2) { // Just a range
                        minDistance = dist;
                        nearestPlant = e;
                    }
                }
            }

            if (nearestPlant != null) {
                self.moveTowards(nearestPlant.getLocation(), 1.0);
                if (self.getLocation().distanceTo(nearestPlant.getLocation()) < self.getSize() + nearestPlant.getSize()) {
                    self.consumeFood(((Plant) nearestPlant).getNutritionValue());
                    nearestPlant.setAlive(false);
                }
                return;
            }
        }

        // Wander randomly
        if (targetWander == null || self.getLocation().distanceTo(targetWander) < 1.0) {
            double randomX = Math.max(0, Math.min(map.getWidth() - 1, self.getLocation().getX() + (Math.random() * 20 - 10)));
            double randomY = Math.max(0, Math.min(map.getHeight() - 1, self.getLocation().getY() + (Math.random() * 20 - 10)));
            targetWander = new Location(randomX, randomY);
        }
        self.moveTowards(targetWander, 0.5); // Wander slowly
    }
}
