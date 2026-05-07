package com.wildlife.model.strategy;

import com.wildlife.model.entity.Animal;
import com.wildlife.model.entity.Entity;
import com.wildlife.model.entity.Rabbit;
import com.wildlife.model.entity.Deer;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.Location;

public class HunterStrategy implements SurvivalStrategy {
    private Location targetWander = null;

    @Override
    public void determineMove(Animal self, GameMap map) {
        if (self.getHunger() < 80) { // Hungry
            Entity prey = null;
            double minDistance = Double.MAX_VALUE;
            for (Entity e : map.getEntities()) {
                if ((e instanceof Rabbit || e instanceof Deer) && e.isAlive()) {
                    double dist = self.getLocation().distanceTo(e.getLocation());
                    if (dist < minDistance && dist <= 200) { // View radius approximation
                        minDistance = dist;
                        prey = e;
                    }
                }
            }

            if (prey != null) {
                self.moveTowards(prey.getLocation(), 1.5); // Sprint!
                if (self.getLocation().distanceTo(prey.getLocation()) < self.getSize() + prey.getSize() + 1.0) {
                    ((Animal) prey).takeDamage(50); // Attack
                    if (!prey.isAlive()) {
                        self.consumeFood(50);
                    }
                }
                return;
            }
        }

        // Wander randomly
        if (targetWander == null || self.getLocation().distanceTo(targetWander) < 1.0) {
            double randomX = Math.max(0, Math.min(map.getWidth() - 1, self.getLocation().getX() + (Math.random() * 40 - 20)));
            double randomY = Math.max(0, Math.min(map.getHeight() - 1, self.getLocation().getY() + (Math.random() * 40 - 20)));
            targetWander = new Location(randomX, randomY);
        }
        self.moveTowards(targetWander, 0.8);
    }
}
