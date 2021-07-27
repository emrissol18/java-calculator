package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import java.awt.event.ActionEvent;

public class PostOperatorActionListener extends AbstractOperatorActionListener {

    private Operation operation;

    public PostOperatorActionListener(Manager manager, Operation operation) {
        super(manager);
        this.operation = operation;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String valueAcc = manager.getValueAcc();
        if( ! valueAcc.isEmpty()) {
            Expression expression = new Expression();
            if (manager.getCurrentExp() != null && manager.getCurrentExp().hasPreOperation()) {
                expression.setPreOperation(manager.getCurrentExp().getPreOperation());
                // end preOperator
                manager.setCurrentExp(null);
            }
            expression.setOperation(operation);
            expression.setValue(valueAcc);
            manager.getExpressions().add(expression);
            manager.setValueAcc("");
        }
    }
}
