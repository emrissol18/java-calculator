package com.emrissol.calc.event.operator;

import com.emrissol.calc.expression.operation.pre.SqrtPreOperation;
import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.ui.UIManager;
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
        // add new expression to current if current is parent (e.g. parent( child( 2 + 2 + [new expression]) ) )
        Expression current = manager.getCurrentExp();
        Expression expression = new Expression();
        expression.getPreOperations().add(new SqrtPreOperation());
        manager.setAndAddCurrentExp(expression);
        System.out.println("uiManager.getScrollPane().getScrollPosition() = " + uiManager.getScrollPane().getScrollPosition());
    }

}
