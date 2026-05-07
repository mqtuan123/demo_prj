package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;

public class Grass extends Plant {
    public Grass(Location location) {
        super(location, 0.5, 10.0); // small size, 10 nutrition
    }
}
