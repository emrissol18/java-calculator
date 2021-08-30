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
        if (current == null || ! uiManager.hasText()) {
            return;
        }
//        System.out.println("current = " + current);
        // TO CLOSE PRE OPERATION
        // expression should hasValue
//        if (current.hasValue() || (current.hasPreOperations() && ! current.isLastPreOperOpen())) {
        if (current.hasValue() || current.isLastPreOperClosed()) {
//            System.out.println("post: if 1");
            current.setOperation(operation);
            if (current.equals(manager.getCurrentExp())) {
                // reset current
                manager.setCurrentExp(null);
            }
            else {
                // reset currentParent
                manager.resetCurrentParent();
            }
        }
        else if (manager.getExpressionQueue().size() > 0 && (manager.hasCurrent() && ! manager.getCurrentValue().isEmpty() )) {
//            System.out.println("post: if 2");
            manager.getExpressionQueue().peek().setOperation(operation);
            uiManager.changeSign(operation);
        }

    }
}
