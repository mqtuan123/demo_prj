package com.wildlife.controller;

import com.wildlife.model.entity.FruitTree;
import com.wildlife.model.entity.Grass;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.Location;
import com.wildlife.model.environment.TerrainType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputController extends MouseAdapter {
    private GameMap map;
    private int panelWidth;
    private int panelHeight;
    private String currentAction = "GRASS"; // "GRASS", "FRUIT", "OBSTACLE"

    public InputController(GameMap map, int panelWidth, int panelHeight) {
        this.map = map;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public void setAction(String action) {
        this.currentAction = action;
    }

    public void updateDimensions(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double scaleX = (double) map.getWidth() / panelWidth;
        double scaleY = (double) map.getHeight() / panelHeight;

        double mapX = e.getX() * scaleX;
        double mapY = e.getY() * scaleY;

        if ("GRASS".equals(currentAction)) {
            map.addEntity(new Grass(new Location(mapX, mapY)));
        } else if ("FRUIT".equals(currentAction)) {
            map.addEntity(new FruitTree(new Location(mapX, mapY)));
        } else if ("OBSTACLE".equals(currentAction)) {
            map.setTerrain((int) mapX, (int) mapY, TerrainType.OBSTACLE);
        }
    }
}
