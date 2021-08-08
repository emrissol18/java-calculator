package com.emrissol.event.operator;

import com.emrissol.Manager;
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
    public void actionPerformed(ActionEvent actionEvent) {
        if (uiManager.getJTextField().getText().isEmpty()) {
            return;
        }

        if (manager.hasCurrent() && manager.getCurrentExp().hasValue()) {
            Expression current = manager.getCurrentExp();
            current.setOperation(operation);
            if (manager.hasCurrentParent() && ! current.equals(manager.getCurrentParentExp())) {
                manager.getCurrentParentExp().addExpression(current);
            }
            manager.addExpressionIfHasNoParent(current);
            manager.setCurrentExp(null);
            uiManager.addBtnTextToTextField(actionEvent);
        }
        else if (manager.getExpressionQueue().size() > 0 && (manager.hasCurrent() && ! manager.getCurrentValue().isEmpty() )) {
            manager.getExpressionQueue().peek().setOperation(operation);
            String f = uiManager.getJTextField().getText();
            f = f.substring(0, f.length()-1);
            uiManager.getJTextField().setText(f + actionEvent.getActionCommand());
        }

    }
}
