package com.emrissol.event.operator;

import com.emrissol.Manager;
import java.awt.event.ActionListener;

public abstract class AbstractOperatorActionListener implements ActionListener {

    protected Manager manager;

    public AbstractOperatorActionListener(Manager manager) {
        this.manager = manager;
    }
}
