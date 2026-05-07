package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.PassiveStrategy;
import com.wildlife.model.strategy.ScaredStrategy;

public class Rabbit extends Animal {
    public Rabbit(Location location) {
        super(location, 0.8, 20, 1.5, 30, 1);
        this.strategy = new ScaredStrategy(new PassiveStrategy()); // Composed strategy
    }
}
