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

        System.err.println("\n\tEQUAL");
        System.out.println("PARENT: " + manager.getCurrentParentExp());
        System.out.println("CURRENT: " + manager.getCurrentExp());
        manager.getExpressionQueue().forEach(System.out::println);
        if (true)
            return;
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
        manager.getExpressionQueue().clear();

        manager.setCurrentParentExp(null);
        manager.setCurrentExp(null);
        uiManager.getJTextField().setText("");
        // calc at the end
        System.err.println("CALC ALL");
    }

}
