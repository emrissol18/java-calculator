package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class ClearAllActionListener extends AbstractOperatorActionListener {
    public ClearAllActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        uiManager.getJTextField().setText("");
        manager.getExpressionQueue().clear();
        manager.setCurrentParentExp(null);
        manager.setCurrentExp(null);
    }
}
