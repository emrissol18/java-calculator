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

        Expression current = manager.getCurrentOrParent();

        if (current == null) {
            return;
        }

        if (current.isParent() && current.isCloseAllowed()) {
            current.closeLastPreOper();
            manager.setCurrentParentExp(current.getParent());
        }
        else if (current.hasParent() && current.getParent().isCloseAllowed()){
            Expression parent = current.getParent();
            parent.closeLastPreOper();
            manager.setCurrentExp(parent);
            manager.setCurrentParentExp(parent.getParent());
        }

    }
}