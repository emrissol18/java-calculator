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
        // if empty or after post oper
        if ( ! manager.hasCurrent()) {
            if (manager.hasCurrentParent()) {
                manager.getCurrentParentExp().addExpression(expression);
            }
            manager.setCurrentExp(expression);
            allowText = true;
        }
        else if (manager.hasCurrentParent()) {
            Expression current = manager.getCurrentExp();
            current.addExpression(expression);
            manager.setCurrentExp(expression);
            manager.setCurrentParentExp(current);
        }
        // if exp#1(exp#2
        else if (manager.hasCurrent()
                && manager.currentHasPreOper()
                && manager.getCurrentValue().isEmpty()
        ) {
            // get exp#1
            Expression current = manager.getCurrentExp();
            // add exp#2 to exp#1
            current.addExpression(expression);
            manager.addExpressionIfHasNoParent(current);
            manager.setCurrentExp(expression);
            manager.setCurrentParentExp(current);
            allowText = true;
        }

        if (allowText) {
            uiManager.addBtnTextToTextField(actionEvent);
        }
    }
}
