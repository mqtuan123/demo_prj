package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.HunterStrategy;

public class Tiger extends Animal {
    public Tiger(Location location) {
        super(location, 1.5, 100, 1.8, 60, 8);
        this.strategy = new HunterStrategy();
    }
}
