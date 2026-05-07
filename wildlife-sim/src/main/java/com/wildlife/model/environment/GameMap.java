package com.wildlife.model.environment;

import com.wildlife.model.entity.Entity;
import com.wildlife.model.entity.Animal;
import com.wildlife.model.entity.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameMap {
    private int width;
    private int height;
    private TerrainType[][] grid;
    private List<Entity> entities;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new TerrainType[width][height];
        this.entities = new CopyOnWriteArrayList<>(); // Thread-safe for iterations
        initDefaultMap();
    }

    private void initDefaultMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = TerrainType.GRASS;
            }
        }
    }

    public void setTerrain(int x, int y, TerrainType type) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = type;
        }
    }

    public TerrainType getTerrain(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return TerrainType.OBSTACLE; // Out of bounds is an obstacle
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void generateArea(int startX, int startY, int w, int h, TerrainType type) {
        for (int i = startX; i < startX + w; i++) {
            for (int j = startY; j < startY + h; j++) {
                setTerrain(i, j, type);
            }
        }
    }
}
