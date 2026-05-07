package com.wildlife.view;

import com.wildlife.model.entity.Entity;
import com.wildlife.model.environment.GameMap;
import java.awt.Graphics;

public interface Renderer {
    void render(Graphics g, GameMap map, int panelWidth, int panelHeight);
    void renderEntity(Graphics g, Entity entity, double scaleX, double scaleY);
}
