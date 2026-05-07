package com.wildlife.view;

import com.wildlife.controller.InputController;
import com.wildlife.controller.UIController;
import com.wildlife.model.engine.SimulationEngine;
import com.wildlife.model.environment.GameMap;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SimulationPanel simulationPanel;
    private GameMap map;
    private UIController uiController;
    private InputController inputController;
    private SimulationEngine engine;

    public MainFrame(GameMap map, SimulationEngine engine) {
        this.map = map;
        this.engine = engine;
        this.uiController = new UIController(map);
        
        setTitle("Wild-Life Eco Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Control Panel
        JPanel controlPanel = new JPanel();
        
        String[] environments = {"Grassland", "Forest", "Lake", "Combined"};
        JComboBox<String> envCombo = new JComboBox<>(environments);
        envCombo.addActionListener(e -> uiController.changeEnvironment((String) envCombo.getSelectedItem()));
        
        String[] renderModes = {"Basic", "Graphic"};
        JComboBox<String> renderCombo = new JComboBox<>(renderModes);
        Renderer basicRenderer = new BasicRenderer();
        Renderer graphicRenderer = new GraphicRenderer();
        renderCombo.addActionListener(e -> {
            if ("Basic".equals(renderCombo.getSelectedItem())) {
                simulationPanel.setRenderer(basicRenderer);
            } else {
                simulationPanel.setRenderer(graphicRenderer);
            }
        });

        String[] actions = {"Plant Grass", "Plant FruitTree", "Place Obstacle"};
        JComboBox<String> actionCombo = new JComboBox<>(actions);
        actionCombo.addActionListener(e -> {
            String selected = (String) actionCombo.getSelectedItem();
            if (selected.contains("Grass")) inputController.setAction("GRASS");
            else if (selected.contains("Fruit")) inputController.setAction("FRUIT");
            else inputController.setAction("OBSTACLE");
        });

        controlPanel.add(new JLabel("Environment:"));
        controlPanel.add(envCombo);
        controlPanel.add(new JLabel("View Mode:"));
        controlPanel.add(renderCombo);
        controlPanel.add(new JLabel("Click Action:"));
        controlPanel.add(actionCombo);

        add(controlPanel, BorderLayout.NORTH);

        // Simulation Area
        simulationPanel = new SimulationPanel(map, basicRenderer);
        add(simulationPanel, BorderLayout.CENTER);

        // Input Setup
        inputController = new InputController(map, 800, 600);
        simulationPanel.addMouseListener(inputController);
        
        // Handle resize properly
        simulationPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                inputController.updateDimensions(simulationPanel.getWidth(), simulationPanel.getHeight());
            }
        });

        pack();
        setLocationRelativeTo(null);
    }
}
