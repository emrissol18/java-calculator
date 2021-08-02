package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.SqrtOperation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class SqrtActionListener extends AbstractOperatorActionListener {

    public SqrtActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Expression expression = new Expression();
        expression.setPreOperation(new SqrtOperation());

        boolean allowText = false;
        if ( ! manager.hasCurrent()) {
            manager.setCurrentExp(expression);
            allowText = true;
        }
        else if (manager.hasCurrent()
                && manager.currentHasPreOper()
                && manager.getCurrentValue().isEmpty() ) {
            Expression current = manager.getCurrentExp();
            current.addExpression(expression);
            manager.addExpressionIfHasNoParent(current);
            manager.setCurrentExp(current.hasParent() ? current.getParent() : null);
            allowText = true;
        }

        if (allowText) {
            uiManager.addBtnTextToTextField(actionEvent);
        }
    }
}
