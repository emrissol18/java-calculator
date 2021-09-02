package com.emrissol.calc.filter;

import com.emrissol.calc.Manager;
import com.emrissol.calc.expression.Expression;
import java.util.Optional;

public class ActionFilter {

    private Manager manager;

    public ActionFilter(Manager manager) {
        this.manager = manager;
    }


    public boolean isDigitsAllowed() {
        Optional<Expression> current = manager.getCurrentOrParent();
        // disallow digits in case of root(1) - here after ')' only post operations are allowed
        if (current.isPresent() && current.get().isParent() && current.get().isLastPreOperClosed()) {
            return false;
        }
        return true;
    }

    public boolean isCloseAllowed(Expression expression) {
        // pre check
        if (expression.hasPreOperations() && expression.hasChildren() && expression.isLastPreOperOpen()) {
            Expression lastChild = expression.getChildren().peekLast();
            // NOT root() - here in root function no value present
            // &&
            // NOT root(1+) - here should be next operation after '1+'
            // &&
            // lastChild's last pre operation must be closed
            if ( lastChild.hasValue() && ! lastChild.hasOperation() && lastChild.isLastPreOperClosed()) {
                return true;
            }
        }
        // not allowed by default
        return false;
    }


    public boolean isPreOperationAllowed() {
        if (manager.hasCurrent() && manager.getCurrentExp().hasValue()) {
            return false;
        }
        return true;
    }
}
