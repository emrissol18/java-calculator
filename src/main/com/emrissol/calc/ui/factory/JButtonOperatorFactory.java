package com.emrissol.calc.ui.factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class JButtonOperatorFactory /*implements JComponentFactory*/ {

    List<? extends ActionListener> listeners;

    public JButtonOperatorFactory() {
    }

    public JButtonOperatorFactory(List<? extends ActionListener> listeners) {
        this.listeners = listeners;
    }

//    @Override
    public JButton create(String text) {
        JButton jButton = new JButton(text);
        jButton.setFont(new Font("Monospace", Font.PLAIN, 20));
//        jButton.setSize(60, 60);
        for (ActionListener listener : listeners) {
            jButton.addActionListener(listener);
        }
        return jButton;
    }

}
