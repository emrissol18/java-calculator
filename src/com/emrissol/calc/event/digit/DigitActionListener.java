package com.emrissol.calc.event.digit;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

// action listener for digit buttons
public class DigitActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(DigitActionListener.class);

    public DigitActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        if ( ! actionFilter.isDigitsAllowed()) {
            logger.log("digits not allowed");
            return;
        }

        Expression newExpression = new Expression();
        newExpression.setValue(actionEvent.getActionCommand());

        if (manager.hasCurrent()) {
            logger.log("hasCurrent");
            Expression current = manager.getCurrentExp();
            if (current.isParent()) {
                logger.log("parent");
//                current.addExpression(newExpression);
//                manager.setCurrentParentExp(current);
//                manager.setCurrentExp(newExpression);
                manager.setAndAddCurrentExp(newExpression);
            }
            else {
                logger.log("! parent");
                current.addToValue(actionEvent.getActionCommand());
            }
        }
        /*else if (manager.hasCurrentParent()) {
            manager.getCurrentParentExp().addExpression(newExpression);
            manager.setCurrentExp(newExpression);
        }*/
        else {
            logger.log(" ! hasCurrent");
//            manager.addExpression(newExpression);
            manager.setAndAddCurrentExp(newExpression);
//            manager.setCurrentExp(newExpression);
        }

    }

}
