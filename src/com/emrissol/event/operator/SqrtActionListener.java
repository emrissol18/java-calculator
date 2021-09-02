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
        if ( ! actionFilter.isPreOperationAllowed()) {
            return;
        }

        Expression expression = new Expression();
        expression.getPreOperations().add(new SqrtOperation());

        // add new expression to current if current is parent (e.g. parent( child( 2 + 2 + [new expression]) ) )
        if (manager.hasCurrent() && manager.getCurrentExp().isParent()) {
            manager.getCurrentExp().addExpression(expression);
            manager.setCurrentExp(expression);
            manager.setCurrentParentExp(manager.getCurrentExp());
        }
        // add new expression to current parent (after another expression) (e.g. parent( child( 2 ) + [new expression] )
        else if (manager.hasCurrentParent()) {
            manager.getCurrentParentExp().addExpression(expression);
            manager.setCurrentExp(expression);
        }
        // simple add new expression (ancestor in new hierarchy)
        else {
            manager.addExpression(expression);
            manager.setCurrentParentExp(expression);
        }

    }

}
