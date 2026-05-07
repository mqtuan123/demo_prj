package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;

public abstract class Plant extends Entity {
    protected double nutritionValue;

    public Plant(Location location, double size, double nutritionValue) {
        super(location, size);
        this.nutritionValue = nutritionValue;
    }

    public double getNutritionValue() { return nutritionValue; }

    @Override
    public void update() {
        // Plants might grow or spread seeds over time
    }
}
