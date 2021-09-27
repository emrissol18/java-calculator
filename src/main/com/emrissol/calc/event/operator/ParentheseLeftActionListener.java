package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.pre.ParentheseLeftOperator;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class ParentheseLeftActionListener extends AbstractOperatorActionListener {

    public ParentheseLeftActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! actionFilter.isPreOperationAllowed()) {
            return;
        }

        Expression expression = new Expression();
        expression.getPreOperations().add(new ParentheseLeftOperator());
        manager.setAndAddCurrentExp(expression);
    }
}
