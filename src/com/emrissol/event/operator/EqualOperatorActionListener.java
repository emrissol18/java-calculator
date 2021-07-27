package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import java.awt.event.ActionEvent;

public class EqualOperatorActionListener extends AbstractOperatorActionListener {

    public EqualOperatorActionListener(Manager manager) {
        super(manager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Expression current = manager.getCurrentExp();

        // if has preOperation
        if (current != null && current.hasPreOperation()) {
            manager.getExpressions().add(manager.getCurrentExp());
        }
        else if (current == null && ! manager.getValueAcc().isEmpty()) {
            Expression expression = new Expression();
            expression.setOperation(Operation.EQUALS);
            expression.setValue(manager.getValueAcc());
            manager.setValueAcc("");
            manager.getExpressions().add(expression);
        }

        manager.getExpressions().forEach(System.out::println);
        manager.getExpressions().clear();
        // calc at the end
        System.err.println("CALC ALL");
    }

}
