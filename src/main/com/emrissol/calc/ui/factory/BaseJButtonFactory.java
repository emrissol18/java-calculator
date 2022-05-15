package com.emrissol.calc.ui.factory;

import com.emrissol.calc.ui.UIManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public abstract class BaseJButtonFactory {

    @Setter
    @Getter
    protected List<? extends ActionListener> listeners;

    protected Font font = UIManager.DEFAULT_FONT;

    public BaseJButtonFactory() {
        this(Collections.emptyList());
    }

    public BaseJButtonFactory(List<? extends ActionListener> listeners) {
        this.listeners = listeners;
    }

    abstract JButton create(String text);

    public void addListeners(JButton jButton) {
        for (ActionListener listener : listeners) {
            jButton.addActionListener(listener);
        }
    }

}
