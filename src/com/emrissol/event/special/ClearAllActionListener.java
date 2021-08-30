package com.emrissol.event.special;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class ClearAllActionListener extends AbstractOperatorActionListener {
    public ClearAllActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        uiManager.clearAll();
        manager.clearAll();
        Expression.resetID();
    }
}
