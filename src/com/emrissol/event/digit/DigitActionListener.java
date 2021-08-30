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
        String log = "";
        Expression expression = new Expression();
        if (manager.hasCurrentParent() && ! manager.hasCurrent()) {
//            System.out.println("ADD TO NEW CURRENT");
            expression.setValue(actionEvent.getActionCommand());
            manager.getCurrentParentExp().addExpression(expression);
            manager.setCurrentExp(expression);
            log ="IF";
        }
        else {
            log = "ELSE";
            if ( ! manager.hasCurrent()) {
                manager.setCurrentExp(expression);
                manager.addExpressionIfHasNoParent(manager.getCurrentExp());
                manager.addToValueOfCurrent(actionEvent.getActionCommand());
                log += " if";
            }
            else {
                log += " else";
                // disalow digit after preOperation's textEnd, i.e. ")".
//                if ( manager.getCurrentExp().hasPreOperations() && ! manager.getCurrentExp().isLastPreOperOpen()) {
                if ( manager.getCurrentExp().isLastPreOperClosed() ) {
                    System.out.println("! manager.getCurrentExp().isLastPreOperClosed() (return)");
                    log += " if";
                    return;
                }
//                System.out.println("ADD TO CURRENT");
                manager.addToValueOfCurrent(actionEvent.getActionCommand());
            }
        }
        logger.log(log);

    }

}
