package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.PassiveStrategy;

public class Elephant extends Animal {
    public Elephant(Location location) {
        super(location, 3.0, 200, 0.8, 40, 9); // High priority, slow speed
        this.strategy = new PassiveStrategy(); // Elephants are mostly peaceful
    }
}
