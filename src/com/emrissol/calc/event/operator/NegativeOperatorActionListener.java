package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class NegativeOperatorActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(NegativeOperatorActionListener.class);

    public NegativeOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        if (manager.hasCurrent()) {
            /*Expression current = manager.getCurrentExp();
            if (current.isNegative()) {
                current.setValue(current.getValue().replaceFirst(Operation.NEGATIVE.getText(), ""));
            }*/
            /*Expression current = manager.getCurrentExp();
            if ( ! current.isParent()) {
                manager.getCurrentExp().toggleNegative();
            }*/
            manager.getCurrentExp().toggleNegative();
        }
        else {
            Expression newExpression = new Expression();
            newExpression.toggleNegative();
            manager.setAndAddCurrentExp(newExpression);
        }

    }

}
