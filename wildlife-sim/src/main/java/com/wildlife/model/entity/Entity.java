package com.wildlife.model.entity;

import com.wildlife.model.environment.Location;

public abstract class Entity {
    protected static int idCounter = 0;
    protected int id;
    protected Location location;
    protected double size;
    protected boolean isAlive = true;

    public Entity(Location location, double size) {
        this.id = ++idCounter;
        this.location = location;
        this.size = size;
    }

    public int getId() { return id; }
    public Location getLocation() { return location; }
    public double getSize() { return size; }
    
    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    public abstract void update();
}
