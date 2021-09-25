package com.emrissol.calc.ui.factory;

import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class JButtonDigitFactory implements JComponentFactory {

    @Setter
    @Getter
    private List<? extends ActionListener> listeners;

    public JButtonDigitFactory(List<? extends ActionListener> listeners) {
        this.listeners = listeners;
    }

    @Override
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