package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.SqrtOperation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class SqrtActionListener extends AbstractOperatorActionListener {

    public SqrtActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        if (manager.hasCurrent() && ! manager.getCurrentValue().isEmpty()) {
            return;
        }

        Expression expression = new Expression();
        expression.getPreOperations().add(new SqrtOperation());

        if ( manager.hasCurrentParent()) {
            manager.getCurrentParentExp().addExpression(expression);
            manager.setCurrentExp(expression);
        }
        else {
            manager.setCurrentParentExp(expression);
            manager.addExpression(expression);
        }

        // add only parent
//        manager.addExpressionIfHasNoParent(expression);

    }

}
