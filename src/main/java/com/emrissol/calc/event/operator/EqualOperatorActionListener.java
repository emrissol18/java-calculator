package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.Operation;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class EqualOperatorActionListener extends AbstractOperatorActionListener {

    public EqualOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        System.err.println("\n\tEQUAL");
//        System.out.println("PARENT: " + manager.getCurrentParentExp());
//        System.out.println("CURRENT: " + manager.getCurrentExp());
//        manager.getExpressionQueue().forEach(System.out::println);
        if ( ! manager.hasCurrent() || (manager.hasCurrent() && manager.getCurrentValue().isEmpty())) {
            return;
        }

        Expression current = manager.getCurrentExp();
        current.setOperation(Operation.EQUALS);
        //temp
//        if (manager.hasCurrentParent() && current != manager.getCurrentParentExp()) {
//            manager.getCurrentParentExp().addExpression(current);
//        }
        manager.addExpressionIfHasNoParent(current);

        manager.getExpressionQueue().forEach(System.out::println);

        manager.setCurrentParentExp(null);
        manager.setCurrentExp(null);
        uiManager.clearAll();
        // calc at the end
        System.err.println("CALC ALL");
    }

}
