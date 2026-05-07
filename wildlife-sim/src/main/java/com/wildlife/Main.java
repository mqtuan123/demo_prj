package com.wildlife;

import com.wildlife.model.engine.SimulationEngine;
import com.wildlife.model.entity.*;
import com.wildlife.model.environment.GameMap;
import com.wildlife.model.environment.Location;
import com.wildlife.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Init Map 100x100 logical units
        GameMap map = new GameMap(100, 100);

        // Populate some initial entities
        for (int i = 0; i < 20; i++) {
            map.addEntity(new Grass(new Location(Math.random() * 100, Math.random() * 100)));
        }
        for (int i = 0; i < 10; i++) {
            map.addEntity(new Rabbit(new Location(Math.random() * 100, Math.random() * 100)));
        }
        for (int i = 0; i < 5; i++) {
            map.addEntity(new Deer(new Location(Math.random() * 100, Math.random() * 100)));
        }
        map.addEntity(new Wolf(new Location(20, 20)));
        map.addEntity(new Tiger(new Location(80, 80)));
        map.addEntity(new Elephant(new Location(50, 50)));

        SimulationEngine engine = new SimulationEngine(map);
        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(map, engine);
            frame.setVisible(true);
            engine.start(); // Start simulation loop
        });
    }
}
