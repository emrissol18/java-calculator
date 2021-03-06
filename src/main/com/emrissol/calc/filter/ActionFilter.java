package com.emrissol.calc.filter;

import com.emrissol.calc.Manager;
import com.emrissol.calc.expression.Expression;

/**
 * Holds static methods for checking conditions for current state.
 */
public class ActionFilter {

    private Manager manager;

    public ActionFilter(Manager manager) {
        this.manager = manager;
    }


    /**
     * Check if digits allowed to be added to current expression.
     *
     * @return true - if digits allowed, otherwise - false.
     */
    public boolean isDigitsAllowed() {
        Expression current = manager.getCurrentExp();
        // allow to start new expression OR add value to operation (like pow operation)
        if (current == null || (current.hasPostOperations() && current.getLastPostOper().isValueAvailable())) {
            return true;
        } else if (
                // disallow right afrer post operation (right after '!')
                current.hasPostOperations()
                // check if parent due only parent could have (closable) pre-post operation
                || current.isParent()
                // if closable pre-post operation has been closed (right after ')')
                && current.isLastPreOperClosed()
        ) {
            return false;
        }
        return true;
    }

    /**
     * Check if closure is allowed for last <i>closable</i> pre operation of current expression at current state.
     *
     * @param expression current expression
     * @return whether close allowed
     */
    public boolean isCloseAllowed(Expression expression) {
        // pre check if there are any operation need to be closed
        if (expression.hasPreOperations() && expression.hasChildren() && ! expression.isLastPreOperClosed()) {
            Expression lastChild = expression.getChildren().peekLast();
            if (
                // NOT root() - here in root function no value present
                lastChild.hasValue()
                &&
                // NOT root(1+) - here should be no operation '+' after digit '1'
                ! lastChild.hasOperation()
                &&
                // NOT root(root( ) lastChild's last pre operation must be closed
                lastChild.isLastPreOperClosed()) {
                return true;
            }
        }
        // not allowed by default
        return false;
    }

    /**
     * Check whether is it allowed to add pre operation at current state.
     *
     * @return true if pre operation allowed, otherwise - false.
     */
    public boolean isPreOperationAllowed() {
        Expression current = manager.getCurrentExp();
        if (manager.hasCurrent() && (current.hasValue() || current.hasPostOperations())) {
            return false;
        }
        return true;
    }

}
