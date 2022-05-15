package com.emrissol.calc;


import com.emrissol.calc.ui.UIManager;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Manager manager = new Manager();
        SwingUtilities.invokeLater( () -> {
            UIManager uiManager = new UIManager(manager);
        });
    }
    
}
