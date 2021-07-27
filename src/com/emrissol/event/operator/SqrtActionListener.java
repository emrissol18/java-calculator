package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.SqrtOperation;
import java.awt.event.ActionEvent;

public class SqrtActionListener extends AbstractOperatorActionListener {

    public SqrtActionListener(Manager manager) {
        super(manager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String valueAcc = manager.getValueAcc();
        if (manager.getCurrentExp() == null && valueAcc.isEmpty()) {
            Expression expression = new Expression();
            expression.setPreOperation(new SqrtOperation());
            manager.setCurrentExp(expression);
        }
    }
}
