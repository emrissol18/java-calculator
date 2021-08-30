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
        if ( manager.getExpressionQueue().isEmpty() || manager.getExpressionQueue().isEmpty()) {
            logger.log("return");
            return;
        }

        resolveCurrent();

        Expression current = manager.getCurrentExp();

        if (current.hasOperation()) {
            current.setOperation(null);
        }

        // first check  IF current has postOperation (right most data)
        else if (current.hasPostOperations()) {
            // SUP_MODE involved
            logger.log("remove PreOperation");
            current.getPreOperations().pollLast();
        }

        // then check if there preOperation any pre operation
        else if (current.hasPreOperations()) {
            // if closed i.e. has ")",  (right most data after post operations)
            if (current.isLastPreOperClosed()) {
                logger.log("preOperation is closed");
                current.setLastPreOperOpen(true);
                // here current MUST HAVE  chilren > 0, so set current's last child as new current
                manager.setCurrentExp(current.peekLastChild());
            }
            else {
                current.removeLastPreOper(manager);
                resetCurrent(current);
            }
        }

        // remove one digit character
        else if ( current.hasValue()) {
            logger.log("value not empty");
            // if expression has value then it could has postOperation or Operation
            logger.log("\tremove last digit");
            try {
                current.removeLastDigit();
            }
            catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
//          if (current.getValue().isEmpty() && ! current.hasPreOperations()) {
            if (current.getValue().isEmpty()) {
                logger.log("\t\texpression is empty");
                resetCurrent(current);
            }

        }
    }

    public void resetCurrent(Expression current) {
        if (current.hasParent()) {
            logger.log("[resetCurrent]: CURRENT HAS PARENT");
            Expression parent = current.getParent();
            parent.removeChild(current);
            manager.setCurrentExp(parent.peekLastChildOrSelf()); // set next (last) child or parent itself
            manager.setCurrentParentExp(parent.getParent()); // might be null, we don't care if so
        }
        else {
            logger.log("[resetCurrent]: CURRENT HAS NOT PARENT");
            manager.setCurrentExp(null);
            manager.setCurrentParentExp(null);
        }
    }

//    private Optional<Expression> resolveCurrent() {
    private void resolveCurrent() {
        if (manager.hasCurrent()) {
            return;
        }
        Expression current = null;
        if (manager.hasCurrentParent()) {
            current = manager.getCurrentParentExp().peekLastChildOrSelf();
        }
        else {
            // get from global list
            current = manager.getExpressionQueue().peekLast().peekLastChildOrSelf();
            manager.setCurrentParentExp(current.getParent());
        }
        manager.setCurrentExp(current);
    }
}
