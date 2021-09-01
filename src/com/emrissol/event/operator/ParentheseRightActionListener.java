package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class ParentheseRightActionListener extends AbstractOperatorActionListener {

    public ParentheseRightActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! uiManager.hasText() || ! manager.hasCurrentParent()) {
            return;
        }

        Expression current = manager.getCurrentParentExp();

        if (current.isCloseAllowed()) {
            current.setLastPreOperOpen(false);
            manager.setCurrentExp(null);
        }
    }
}