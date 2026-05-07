package com.wildlife.view;

import com.wildlife.model.environment.GameMap;
import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    private GameMap map;
    private Renderer renderer;

    public SimulationPanel(GameMap map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
        setPreferredSize(new Dimension(800, 600));
        
        // Timer to repaint at ~60fps
        Timer timer = new Timer(16, e -> repaint());
        timer.start();
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (map != null && renderer != null) {
            renderer.render(g, map, getWidth(), getHeight());
        }
    }
}
