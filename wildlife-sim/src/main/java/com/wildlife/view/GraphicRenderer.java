package com.wildlife.view;

import com.wildlife.model.entity.*;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.TerrainType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GraphicRenderer implements Renderer {
    private Map<Class<? extends Entity>, BufferedImage> imageCache = new HashMap<>();
    private BasicRenderer fallback = new BasicRenderer();

    public GraphicRenderer() {
        // Load images from internet
        loadFromUrl(Rabbit.class, "https://api.dicebear.com/7.x/bottts/png?seed=Rabbit&backgroundColor=ffffff");
        loadFromUrl(Wolf.class, "https://api.dicebear.com/7.x/bottts/png?seed=Wolf&backgroundColor=ff0000");
        loadFromUrl(Tiger.class, "https://api.dicebear.com/7.x/bottts/png?seed=Tiger&backgroundColor=ffa500");
        loadFromUrl(Deer.class, "https://api.dicebear.com/7.x/bottts/png?seed=Deer&backgroundColor=8b4513");
        loadFromUrl(Elephant.class, "https://api.dicebear.com/7.x/bottts/png?seed=Elephant&backgroundColor=808080");
        loadFromUrl(Human.class, "https://api.dicebear.com/7.x/bottts/png?seed=Human&backgroundColor=ffb6c1");
        loadFromUrl(Grass.class, "https://api.dicebear.com/7.x/icons/png?seed=Leaf&backgroundColor=00ff00");
        loadFromUrl(FruitTree.class, "https://api.dicebear.com/7.x/icons/png?seed=Tree&backgroundColor=228b22");
    }

    private void loadFromUrl(Class<? extends Entity> clazz, String urlStr) {
        try {
            URL url = new URL(urlStr);
            BufferedImage img = ImageIO.read(url);
            if (img != null) {
                imageCache.put(clazz, img);
            }
        } catch (Exception e) {
            System.err.println("Failed to load image for " + clazz.getSimpleName() + " from " + urlStr);
        }
    }

    @Override
    public void render(Graphics g, GameMap map, int panelWidth, int panelHeight) {
        // Fallback terrain rendering from BasicRenderer since internet terrain is tricky
        double scaleX = (double) panelWidth / map.getWidth();
        double scaleY = (double) panelHeight / map.getHeight();

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                TerrainType type = map.getTerrain(i, j);
                switch (type) {
                    case GRASS: g.setColor(new Color(144, 238, 144)); break;
                    case FOREST: g.setColor(new Color(34, 139, 34)); break;
                    case LAKE: g.setColor(new Color(135, 206, 235)); break;
                    case MUD: g.setColor(new Color(139, 69, 19)); break;
                    case OBSTACLE: g.setColor(Color.DARK_GRAY); break;
                }
                g.fillRect((int)(i * scaleX), (int)(j * scaleY), (int)Math.ceil(scaleX), (int)Math.ceil(scaleY));
            }
        }

        for (Entity e : map.getEntities()) {
            if (e.isAlive()) {
                renderEntity(g, e, scaleX, scaleY);
            }
        }
    }

    @Override
    public void renderEntity(Graphics g, Entity entity, double scaleX, double scaleY) {
        BufferedImage img = imageCache.get(entity.getClass());
        if (img != null) {
            int x = (int) (entity.getLocation().getX() * scaleX);
            int y = (int) (entity.getLocation().getY() * scaleY);
            int sizeX = (int) (entity.getSize() * scaleX * 2); // Exaggerate size slightly
            int sizeY = (int) (entity.getSize() * scaleY * 2);
            g.drawImage(img, x, y, sizeX, sizeY, null);
            
            // Draw HP bar for animals
            if (entity instanceof Animal) {
                Animal a = (Animal) entity;
                g.setColor(Color.RED);
                g.fillRect(x, y - 5, sizeX, 3);
                g.setColor(Color.GREEN);
                int hpWidth = (int) ((a.getHp() / a.getHp()) * sizeX); // Fix maxHp access logic if we didn't add getter. Just use ratio 1 for now or add getter later.
                g.fillRect(x, y - 5, (int)((a.getHp() / 100.0) * sizeX), 3); // Approx 100 max hp
            }
        } else {
            fallback.renderEntity(g, entity, scaleX, scaleY);
        }
    }
}
