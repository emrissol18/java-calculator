package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class PostOperatorActionListener extends AbstractOperatorActionListener {

    private Operation operation;

    public PostOperatorActionListener(Manager manager, UIManager uiManager, Operation operation) {
        super(manager, uiManager);
        this.operation = operation;
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        Expression current = manager.getCurrentOrParent();
        if ( ! manager.hasExpressions()) {
            return;
        }

        if (current == null) {
            System.out.println("Change sign");
            manager.changeOperationOfLast(operation);
        }
        else if (current.hasValue() || current.isLastPreOperClosed() || current.hasChildren()) {
            current.setOperation(operation);

            // reset
            if (current.isParent()) {
                manager.setCurrentParentExp(null);
            }
            else {
                manager.setCurrentExp(null);
            }
            if (current.isLastPreOperClosed()) {
                manager.setLastExp(current);
            }
        }

    }
}
