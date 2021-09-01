package com.emrissol.event.digit;

import com.emrissol.Manager;
import com.emrissol.dev.log.Logger;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
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
            Expression current = manager.getCurrentExp();
            if (current.isParent()) {
                current.addExpression(newExpression);
                manager.setCurrentParentExp(current);
                manager.setCurrentExp(newExpression);
            }
            else {
                current.addToValue(actionEvent.getActionCommand());
            }
        }
        else if (manager.hasCurrentParent()) {
            manager.getCurrentParentExp().addExpression(newExpression);
            manager.setCurrentExp(newExpression);
        }
        else {
            manager.addExpression(newExpression);
            manager.setCurrentExp(newExpression);
        }

    }

}
