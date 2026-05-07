package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.HunterStrategy;

public class Wolf extends Animal {
    public Wolf(Location location) {
        super(location, 1.0, 60, 1.6, 50, 5);
        this.strategy = new HunterStrategy();
    }
}
