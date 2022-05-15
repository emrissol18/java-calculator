package com.emrissol.calc.ui.factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class JButtonOperatorFactory extends BaseJButtonFactory {

    public JButtonOperatorFactory() {
        this(Collections.emptyList());
    }

    public JButtonOperatorFactory(List<? extends ActionListener> listeners) {
        super(listeners);
        this.font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    }

    public JButton create(String text) {
        JButton jButton = new JButton(text);
        jButton.setFont(font);
        addListeners(jButton);
        return jButton;
    }

}
