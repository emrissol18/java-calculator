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
        if ( ! uiManager.hasText()) {
            return;
        }

        Expression current = manager.getCurrentExp();
        if (manager.hasCurrent()) {
            if (current.isCloseAllowed()) {
                System.out.println("if 1 1");
                current.getLastPreOper().setOpen(false);
                manager.resetParent(current);
            }
            else if (current.hasParent()) {
                System.out.println("if 1 2");
                current = current.getParent();
                if (current.isCloseAllowed()) {
                    System.out.println("if 1 3");
                    manager.closePreOper(current);
                }
            }
        }
        else if (manager.hasCurrentParent()) {
            System.out.println("if 2 1");
            current = manager.getCurrentParentExp();
            if (current.isCloseAllowed()) {
                System.out.println("if 2 2");
                manager.closePreOper(current);
            }
        }
        /*Expression currentOrParent = manager.getCurrentOrParent();
        if (currentOrParent == null || currentOrParent.hasOperation() || ! currentOrParent.hasParent()) {
            return;
        }
        AbstractPrePostOperation preOperToClose = currentOrParent.getLastPreOper();
        if ( ! currentOrParent.hasPreOperations() && currentOrParent.hasParent() ) {
            currentOrParent = currentOrParent.getParent();
            if (currentOrParent.hasChildren() && currentOrParent.getChildren().peekLast().hasValue()) {
                preOperToClose = currentOrParent.getParent().getLastPreOper();
            }
        }
        if (preOperToClose != null) {
            preOperToClose.setClosed(true);
            manager.setCurrentExp(null);
            manager.resetCurrentParent();
        }*/
    }
}