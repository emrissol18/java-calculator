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

//        logger.setActive(false);
        // can be null
        Expression current = manager.getCurrentExp();

        // take peek last expression from global list if no current present
        if ( ! manager.hasCurrent() ) {
            logger.log("peek last");
            current = manager.peekLastExp();
            manager.setCurrentExp(current.getDescendantChildOrItself());
        }

        if (current.hasOperation()) {
            logger.log("remove operation");
            current.setOperation(null);
            return;
        }

        // TO_DO POST OPERATIONS GO HERE

        if (current.hasPreOperations() ) {
            logger.log("isParent");

            // open pre operation
            if ( current.isLastPreOperClosed()) {
                logger.log("open pre operation");
                current.setLastPreOperOpen(true);
                manager.setCurrentExp(current.peekLastChild());
//                    manager.setCurrentParentExp(current);
            }

            // try to remove last one preOoperation
            else if ( ! current.hasChildren()) {
                logger.log("current has no children");
                // here last preOperation looks like combination of opreOper.htmlStart + opreOper.textStart
                // remove from parent or global list if has no preOperations
                logger.log("remove last pre operation");
                current.removeLastPreOper();
//                if (current.removeLastPreOperAndIsHasNoPreOperations()) {
                if ( ! current.hasPostOperations()) {
                    logger.log("no more pre operations");
                    manager.setCurrentExp(null);
                    if ( ! current.detachFromParent()) {
                        logger.log("remove from global list (last preOperation)");
                        manager.removeExpression(current);
                    }
                }
            }
            else if (current.lastChildHasOperation()) {
                logger.log("current's last child has operation");
                Expression lastChild = current.peekLastChild();
                lastChild.setOperation(null);
                manager.setCurrentExp(lastChild);
            }
        }
        else {
            logger.log("remove last digit");
            // remove last digit
            if (current.removeLastDigitAndIsEmpty()) {
                logger.log("expression is empty, remove from parent");
                // set parent or null
                manager.setCurrentExp(current.getParent());
                // try remove from parent, if current has no parent then remove current from global list
                if ( ! current.detachFromParent()) {
                    logger.log("no parent present, remove from global list");
                    manager.removeExpression(current);
                }
            }
        }

//        System.out.println("CURRENT AFTER = " + current);
//        logger.log("current = " + (current != null ? current.getLayout() : null));
    }

}
