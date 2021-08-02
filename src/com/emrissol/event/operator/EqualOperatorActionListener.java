package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class EqualOperatorActionListener extends AbstractOperatorActionListener {

    public EqualOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if ( ! manager.hasCurrent() || (manager.hasCurrent() && manager.getCurrentValue().isEmpty())) {
            return;
        }

        Expression current = manager.getCurrentExp();
        current.setOperation(Operation.EQUALS);
        manager.getExpressions().add(current);

        manager.getExpressions().forEach(System.out::println);
        manager.getExpressions().clear();

        manager.setCurrentExp(null);
        uiManager.getJTextField().setText("");
        // calc at the end
        System.err.println("CALC ALL");
    }

}
