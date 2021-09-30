package com.emrissol.calc.filter;

import com.emrissol.calc.Manager;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.post.FactorialPostOperation;

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
        }
        else if (
                        // disallow right afrer post operation (right after '!')
                       current.hasPostOperations()
                            ||
                            // OR if closable pre-post operation has been closed (right after ')')
                            current.isParent() // check if parent due only parent could have (closable) pre-post operation
                                &&
                                (current.isLastPreOperClosable() && current.isLastPreOperClosed())
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
     * Wether is it valid to add pre operation at current state.
     *
     * @return true if pre operation allowed, otherwise - false.
     */
    public boolean isPreOperationAllowed() {
        Expression current = manager.getCurrentExp();
        if (manager.hasCurrent() &&
                // disallow if current != null AND (already has value OR has post operations)
                (current.hasValue() || current.hasPostOperations())) {
            return false;
        }
        return true;
    }

    /**
     * Check if it is valid to add factorial post operation to current expression.<br/>
     * <small>(<i>this method supposedly can be refactored to</i> isPostOperationAllowed() <i>method</i>)</small>.
     *
     * @return true if factorial allowed, otherwise - false.
     */
    public boolean isFactorialAllowed() {
        if ( ! manager.hasCurrent()) {
            return false;
        }
        Expression current = manager.getCurrentExp();

        boolean hasFactorial = current.hasPostOperations()
                && current.getPostOperations().stream().anyMatch( o -> o instanceof FactorialPostOperation);

        return  ! hasFactorial // no two factorials in a row
                &&
                (
                        // curent has value AND has no operation
                        ( current.hasValue() && ! current.hasOperation())
                        ||
                        // OR allow if last closable operation closed and there no simple operation (+,-,*...) yet
                        (current.isLastPreOperClosed() && ! current.hasOperation())
                );
    }
}
