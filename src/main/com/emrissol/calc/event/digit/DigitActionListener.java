package com.emrissol.calc.event.digit;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

/**
 * Action listener for digit buttons from 0 to 9
 * */
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
        Expression current = manager.getCurrentExp();

        if (manager.hasCurrent()) {
            logger.log("hasCurrent");
            if (current.hasPostOperations() && current.getLastPostOper().isValueAvailable()) {
                AbstractPrePostOperation lastPost = current.getLastPostOper();
                lastPost.setValue(lastPost.getValue().concat(actionEvent.getActionCommand()).trim());
            } else if (current.isParent()) {
                logger.log("parent");
                manager.setAndAddCurrentExp(newExpression);
            } else {
                logger.log("! parent");
                current.addToValue(actionEvent.getActionCommand());
            }
        } else {
            logger.log(" ! hasCurrent");
            manager.setAndAddCurrentExp(newExpression);
        }

    }

}
