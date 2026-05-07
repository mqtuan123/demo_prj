package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.PassiveStrategy;

public class Human extends Animal {
    public Human(Location location) {
        super(location, 1.0, 100, 1.5, 80, 10); // Highest priority, no natural predators here
        this.strategy = new PassiveStrategy(); // Humans just wander in this simple sim unless controlled
    }
}
