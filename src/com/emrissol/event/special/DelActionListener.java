package com.emrissol.event.special;

import com.emrissol.Manager;
import com.emrissol.dev.log.Logger;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
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

        // RESOLVE CURRENT
        Expression current = manager.getCurrentOrParent();
        System.out.println("CURRENT (before): " + (current != null ? current.getLayout() : null));
        // ELSE get from GLOBAL QUEUE
        if (current == null) {
            current = manager.getExpressionQueue().peekLast();
            if (current.isParent()) {
                manager.setCurrentParentExp(current);
            }
            else {
                // simple expression
                manager.setCurrentExp(current);
            }
        }
        System.out.println("CURRENT (after): " + current.getLayout());
        boolean isParent = current.isParent();
        System.out.println("isParent = " + isParent);

        // parent or child
        if (current.hasOperation()) {
            logger.log("remove operation");
            current.setOperation(null);
        }
        // parent
        else if (isParent && current.hasPreOperations()) {
            if (current.isLastPreOperOpen()) {
                // remove last pre oper and if it was last pre oper then set currentParent as current's parent or null
                manager.removeLastPreOperAndResetParent(current);
            }
            else {
                current.setLastPreOperOpen(true);
                manager.setCurrentExp(current.peekLastChild());
                logger.log("remove last pre operation");
            }
        }
        // child
        else {
            logger.log("remove last digit");
//            current.removeLastDigitAndReset(manager);
            manager.removeLastDigitAndResetCurrent(current);
        }
//        logger.log("current: " + (manager.hasCurrent() ? manager.getCurrentExp().getLayout() : null));
//        logger.log("currentParent: " + (manager.hasCurrentParent() ? manager.getCurrentParentExp().getLayout() : null));

    }

}
