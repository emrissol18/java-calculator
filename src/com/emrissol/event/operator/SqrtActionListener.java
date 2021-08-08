package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;
import java.util.NavigableMap;

public class SqrtActionListener extends AbstractOperatorActionListener {

    public SqrtActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (manager.hasCurrent() && ! manager.getCurrentValue().isEmpty()) {
            return;
        }

        Expression expression = new Expression(manager.getPreOperation(Operation.SQRT));

        if ( manager.hasCurrentParent()) {
            manager.getCurrentParentExp().addExpression(expression);
        }
        else {
            manager.setCurrentParentExp(expression);
        }

        // add only parent
        manager.addExpressionIfHasNoParent(expression);

        manager.setCurrentExp(expression);

        uiManager.addBtnTextToTextField(Operation.SQRT.getText());
    }

}
