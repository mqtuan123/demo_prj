package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;

public class FruitTree extends Plant {
    public FruitTree(Location location) {
        super(location, 1.5, 30.0); // larger size, 30 nutrition
    }
}
