package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.pre.NegativePreOperation;
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
            Expression current = manager.getCurrentExp();
            if (current.isParent() && current.lastChildHasOperation()) {
                Expression newExpression = new Expression();
                toggleNegative(newExpression);
                newExpression.getPreOperations().add(new NegativePreOperation());
                manager.setAndAddCurrentExp(newExpression);
            }
            else {
                toggleNegative(manager.getCurrentExp());
            }
        }
        /*else {
            Expression newExpression = new Expression();
            toggleNegative(newExpression);
            manager.setAndAddCurrentExp(newExpression);
        }*/

    }

    private void toggleNegative(Expression exp) {
        if (exp.hasPreOperations() && exp.getPreOperations().stream().anyMatch( o -> o instanceof NegativePreOperation)) {
            exp.getPreOperations().removeIf( o -> o instanceof NegativePreOperation);
        }
        else {
            NegativePreOperation negativePreOperation = new NegativePreOperation();
            if (exp.hasPreOperations() && ! exp.hasParent()) {
                negativePreOperation.setTextStart(OperatorText.NEGATIVE_LAYOUT);
                negativePreOperation.setTextEnd("");
            }
            exp.getPreOperations().add(negativePreOperation);
        }
    }
}
