package com.emrissol.calc.event.special;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class DelActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(DelActionListener.class);

    private StringBuilder stringBuilder = new StringBuilder();

    public DelActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.hasExpressions()) {
            logger.log("return");
            return;
        }

        Expression current = null;

        if ( ! manager.hasCurrent() && ! manager.hasCurrentParent()) {
            current = manager.peekLastExp();
            if (current.isParent() && current.isLastPreOperClosed()) {
                manager.setCurrentExp(current.peekLastChild());
                manager.setCurrentParentExp(current);
            }
            else {
                manager.setCurrentExp(current);
            }
        }

        System.out.println("current = " + (current != null ? current.getLayout() : null));
        if (current != null && current.hasOperation()) {
            logger.log("remove operation");
            current.setOperation(null);
            return;
        }

        if (manager.hasCurrent()) {
            current = manager.getCurrentExp();

            if (current.hasPreOperations() ) {

                // open pre opeartion
                if ( ! current.isLastPreOperOpen()) {
                    logger.log("open pre operation");
                    current.setLastPreOperOpen(true);
                    manager.setCurrentParentExp(current);
                }
                // root(
                else if ( ! current.hasChildren()) {
                    if (current.removeLastPreOperIfHasPreOperations()) {
                        logger.log("remove last pre operation");
                        logger.log("no more pre operations");
//                        manager.setCurrentExp(current.peekLastChild());
                        manager.setCurrentExp(null);
                    }
                }
            }
            else {
                logger.log("remove last digit");
                if (current.removeLastDigitTrueIfEmpty()) {
                    logger.log("expression is empty, remove from parent");
                    manager.setCurrentExp(null);
                    if ( ! current.detachFromParent()) {
                        logger.log("no parent present, remove from global lsit");
                        manager.removeExpression(current);
                    }
                }
            }
        }
        else {
            current = manager.getCurrentParentExp();
            logger.log("else (hasParent())");
        }

    }

}
