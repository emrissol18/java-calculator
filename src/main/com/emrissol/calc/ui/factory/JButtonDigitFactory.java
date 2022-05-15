package com.emrissol.calc.ui.factory;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class JButtonDigitFactory extends BaseJButtonFactory {

    public JButtonDigitFactory() {
        this(Collections.emptyList());
    }

    public JButtonDigitFactory(List<? extends ActionListener> listeners) {
        super(listeners);
    }

    @Override
    public JButton create(String text) {
        JButton jButton = new JButton(text);
        jButton.setFont(font);
        jButton.setActionCommand(text);
        addListeners(jButton);
        return jButton;
    }

}
