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
            logger.log("resolve current");
            current = manager.peekLastExp();
            if (current.isParent() && current.isLastPreOperClosed()) {
                manager.setCurrentExp(current.peekLastChild());
                manager.setCurrentParentExp(current);
            }
            else {
                manager.setCurrentExp(current);
            }
        }

//        System.out.println("current = " + (current != null ? current.getLayout() : null));
        System.out.println(current);
        if (current != null && current.hasOperation()) {
            logger.log("remove operation");
            current.setOperation(null);
            return;
        }

//        if (true) return;
        if (manager.hasCurrent()) {
            logger.log("current");
            current = manager.getCurrentExp();

            if (current.isParent() ) {
                logger.log("isParent");
                // open pre opeartion
                if ( current.isLastPreOperClosed()) {
                    logger.log("open pre operation");
                    current.setLastPreOperOpen(true);
                    manager.setCurrentExp(current.peekLastChild());
                    manager.setCurrentParentExp(current);
                }
                // try to remove one pre operation
                else if ( ! current.hasChildren()) {
                    if (current.removeLastPreOperIfHasPreOperations()) {
                        logger.log("remove last pre operation");
                        logger.log("no more pre operations");
                        manager.setCurrentExp(null);
                        current.detachFromParent();
                        if (manager.hasCurrentParent()) {
                            manager.setCurrentParentExp(current.getParent());
                        }
                    }
                }
            }
            else {
                logger.log("remove last digit");
                current = manager.getCurrentParentExp();
                if (current.removeLastDigitTrueIfEmpty()) {
                    logger.log("expression is empty, remove from parent");
                    current.detachFromParent();
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
            logger.log("currentParent");
            if (current.hasPreOperations() && current.removeLastPreOperIfHasPreOperations()) {
                manager.removeExpression(current);
//                manager.setCurrentExp(null);
                manager.setCurrentParentExp(null);
            }
        }

//        System.out.println("CURRENT AFTER = " + current);
        System.out.println("current = " + (current != null ? current.getLayout() : null));
    }

}
