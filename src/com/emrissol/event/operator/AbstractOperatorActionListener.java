package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionListener;

public abstract class AbstractOperatorActionListener implements ActionListener {

    protected Manager manager;
    protected UIManager uiManager;

    public AbstractOperatorActionListener(Manager manager, UIManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
    }
}
