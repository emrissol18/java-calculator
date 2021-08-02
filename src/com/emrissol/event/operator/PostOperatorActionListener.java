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

        if (manager.hasCurrent()) {
            Expression current = manager.getCurrentExp();
            manager.setCurrentExp(null);
            current.setOperation(operation);
            uiManager.addBtnTextToTextField(actionEvent);
            manager.addExpressionIfHasNoParent(current);
        }
        else if (manager.getExpressions().size() > 0 && (manager.hasCurrent() && ! manager.currentHasPreOper())) {
            manager.getExpressions().peek().setOperation(operation);
            String f = uiManager.getJTextField().getText();
            f = f.substring(0, f.length()-1);
            uiManager.getJTextField().setText(f + actionEvent.getActionCommand());
        }

    }
}
