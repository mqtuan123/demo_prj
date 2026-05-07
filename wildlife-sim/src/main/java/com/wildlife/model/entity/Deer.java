package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;
import com.wildlife.model.strategy.PassiveStrategy;
import com.wildlife.model.strategy.ScaredStrategy;

public class Deer extends Animal {
    public Deer(Location location) {
        super(location, 1.2, 40, 1.2, 40, 2);
        this.strategy = new ScaredStrategy(new PassiveStrategy());
    }
}
