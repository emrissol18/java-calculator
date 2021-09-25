package com.emrissol.calc.event.special;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.ui.UIManager;
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
