package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.SurvivalStrategy;
import com.wildlife.model.environment.GameMap;

public abstract class Animal extends Entity {
    protected double hp;
    protected double maxHp;
    protected double hunger;
    protected double thirst;
    protected double baseSpeed;
    protected double viewRadius;
    protected SurvivalStrategy strategy;
    protected double priority; // For path yielding: larger number = higher priority

    public Animal(Location location, double size, double maxHp, double baseSpeed, double viewRadius, double priority) {
        super(location, size);
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.hunger = 100; // 100 is full, 0 is starving
        this.thirst = 100; // 100 is quenched, 0 is dehydrated
        this.baseSpeed = baseSpeed;
        this.viewRadius = viewRadius;
        this.priority = priority;
    }

    public void setStrategy(SurvivalStrategy strategy) {
        this.strategy = strategy;
    }

    public SurvivalStrategy getStrategy() { return strategy; }

    public double getHp() { return hp; }
    public double getHunger() { return hunger; }
    public double getThirst() { return thirst; }
    public double getPriority() { return priority; }
    
    public void consumeFood(double amount) {
        hunger = Math.min(100, hunger + amount);
        hp = Math.min(maxHp, hp + amount / 2);
    }

    public void consumeWater(double amount) {
        thirst = Math.min(100, thirst + amount);
    }

    public void takeDamage(double amount) {
        hp -= amount;
        if (hp <= 0) {
            isAlive = false;
        }
    }

    public void moveTowards(Location target, double speedModifier) {
        double dx = target.getX() - location.getX();
        double dy = target.getY() - location.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            double speed = baseSpeed * speedModifier;
            // Prevent overshooting
            if (speed > distance) speed = distance;
            
            location.setX(location.getX() + (dx / distance) * speed);
            location.setY(location.getY() + (dy / distance) * speed);
        }
    }

    public void determineAction(GameMap map) {
        if (!isAlive) return;
        
        // Biological decay
        hunger -= 0.05;
        thirst -= 0.1;
        
        if (hunger <= 0) takeDamage(0.1);
        if (thirst <= 0) takeDamage(0.2);

        if (strategy != null && isAlive) {
            strategy.determineMove(this, map);
        }
    }

    @Override
    public void update() {
        // Will be called by engine, but determineAction is the main one
    }
}
