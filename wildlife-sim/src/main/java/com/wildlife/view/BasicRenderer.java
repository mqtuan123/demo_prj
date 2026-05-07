package com.wildlife.view;

import com.wildlife.model.entity.*;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.TerrainType;

import java.awt.*;

public class BasicRenderer implements Renderer {

    @Override
    public void render(Graphics g, GameMap map, int panelWidth, int panelHeight) {
        double scaleX = (double) panelWidth / map.getWidth();
        double scaleY = (double) panelHeight / map.getHeight();

        // Draw Terrain
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                TerrainType type = map.getTerrain(i, j);
                switch (type) {
                    case GRASS: g.setColor(new Color(144, 238, 144)); break; // Light green
                    case FOREST: g.setColor(new Color(34, 139, 34)); break; // Dark green
                    case LAKE: g.setColor(new Color(135, 206, 235)); break; // Sky blue
                    case MUD: g.setColor(new Color(139, 69, 19)); break; // Saddle brown
                    case OBSTACLE: g.setColor(Color.DARK_GRAY); break;
                }
                g.fillRect((int)(i * scaleX), (int)(j * scaleY), (int)Math.ceil(scaleX), (int)Math.ceil(scaleY));
            }
        }

        // Draw Entities
        for (Entity e : map.getEntities()) {
            if (e.isAlive()) {
                renderEntity(g, e, scaleX, scaleY);
            }
        }
    }

    @Override
    public void renderEntity(Graphics g, Entity entity, double scaleX, double scaleY) {
        int x = (int) (entity.getLocation().getX() * scaleX);
        int y = (int) (entity.getLocation().getY() * scaleY);
        int size = (int) (entity.getSize() * Math.max(scaleX, scaleY));

        if (entity instanceof Rabbit) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, size, size);
        } else if (entity instanceof Deer) {
            g.setColor(new Color(205, 133, 63)); // Peru
            g.fillOval(x, y, size, size);
        } else if (entity instanceof Wolf) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, size, size);
        } else if (entity instanceof Tiger) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, size, size);
        } else if (entity instanceof Elephant) {
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x, y, size, size);
        } else if (entity instanceof Human) {
            g.setColor(Color.PINK);
            g.fillOval(x, y, size, size);
        } else if (entity instanceof Grass) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, size, size);
        } else if (entity instanceof FruitTree) {
            g.setColor(Color.RED);
            g.fillOval(x, y, size, size);
        }
    }
}
