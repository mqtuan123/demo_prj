package com.wildlife.controller;

import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.TerrainType;

public class UIController {
    private GameMap map;

    public UIController(GameMap map) {
        this.map = map;
    }

    public void changeEnvironment(String envType) {
        switch (envType) {
            case "Grassland":
                map.generateArea(0, 0, map.getWidth(), map.getHeight(), TerrainType.GRASS);
                break;
            case "Forest":
                map.generateArea(0, 0, map.getWidth(), map.getHeight(), TerrainType.FOREST);
                break;
            case "Lake":
                map.generateArea(0, 0, map.getWidth(), map.getHeight(), TerrainType.LAKE);
                break;
            case "Combined":
                map.generateArea(0, 0, map.getWidth()/2, map.getHeight()/2, TerrainType.FOREST);
                map.generateArea(map.getWidth()/2, 0, map.getWidth()/2, map.getHeight()/2, TerrainType.GRASS);
                map.generateArea(0, map.getHeight()/2, map.getWidth(), map.getHeight()/2, TerrainType.LAKE);
                break;
        }
    }
}
