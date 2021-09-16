package com.emrissol.calc.filter;

import com.emrissol.calc.Manager;
import com.emrissol.calc.expression.Expression;

public class ActionFilter {

    private Manager manager;

    public ActionFilter(Manager manager) {
        this.manager = manager;
    }


    public boolean isDigitsAllowed() {
        Expression current = manager.getCurrentExp();
        // disallow digits in case of root(1) - here after ')' only post operations are allowed
//        boolean isFactorialAllowed = isFactorialAllowed();
//        System.out.println("isFactorialAllowed = " + isFactorialAllowed);

        if (current == null || (current.hasPostOperations() && current.getLastPostOper().isValueAvailable())) {
            return true;
        }
        else if (
                       current.hasPostOperations()
                            ||
                            current.isParent()
                                &&
                                (current.isLastPreOperClosable() && current.isLastPreOperClosed())
        ) {
            return false;
        }
        return true;
    }

    public boolean isCloseAllowed(Expression expression) {
        // pre check
        if (expression.hasPreOperations() && expression.hasChildren() && ! expression.isLastPreOperClosed()) {
            Expression lastChild = expression.getChildren().peekLast();
            // NOT root() - here in root function no value present
            // &&
            // NOT root(1+) - here should be next operation after '1+'
            // &&
            // NOT root(root( ) lastChild's last pre operation must be closed
            if ( lastChild.hasValue() && ! lastChild.hasOperation() && lastChild.isLastPreOperClosed()) {
                return true;
            }
        }
        // not allowed by default
        return false;
    }


    public boolean isPreOperationAllowed() {
        Expression current = manager.getCurrentExp();
        if (manager.hasCurrent() && (current.hasValue() || current.hasPostOperations())) {
            return false;
        }
        return true;
    }

    public boolean isFactorialAllowed() {
        if ( ! manager.hasCurrent()) {
            return false;
        }
        Expression current = manager.getCurrentExp();
        boolean hasFactorial = current.hasFactorial();
        System.out.println("hasFactorial = " + hasFactorial);
        return  ! hasFactorial
                &&
                (
                        // current is not parent, has value and has no operation
                        ( ! current.isParent() && current.hasValue() && ! current.hasOperation())
                        ||
                        // parent
                        (current.isLastPreOperClosed() && ! current.hasOperation())
                );
    }
}
