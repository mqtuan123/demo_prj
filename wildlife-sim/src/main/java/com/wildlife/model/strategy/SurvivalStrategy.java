package com.wildlife.model.strategy;

import com.wildlife.model.entity.Animal;
import com.wildlife.model.environment.GameMap;

public interface SurvivalStrategy {
    void determineMove(Animal self, GameMap map);
}
