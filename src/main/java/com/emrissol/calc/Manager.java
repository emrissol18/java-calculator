package com.emrissol.calc;

import com.emrissol.calc.expression.operation.SqrtOperation;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.Operation;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

// singleton
public class Manager {

    private static Manager instance;

    private Manager() {
        preOperationMap.put(Operation.SQRT, new SqrtOperation());
        // here add more
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    @Getter
    @Setter
    private Expression currentParentExp = null;

    @Getter
    @Setter
    private Expression currentExp = null;

    @Getter
    private Deque<Expression> expressionQueue = new LinkedList<>();

    private Map<Operation, AbstractPrePostOperation> preOperationMap = new HashMap<>();

    public void addExpression(Expression expression) {
        getExpressionQueue().add(expression);
    }
    public void addExpressionIfHasNoParent(Expression expression) {
        if ( ! expression.hasParent() && ! expressionQueue.contains(expression)) {
            addExpression(expression);
        }
    }

    public boolean hasCurrent() {
        return currentExp != null;
    }

    public boolean hasCurrentParent() {
        return currentParentExp != null;
    }

    public void addToValueOfCurrent(String text) {
        currentExp.setValue(currentExp.getValue() + text);
    }

    public String getCurrentValue() {
        return currentExp.getValue();
    }

    public AbstractPrePostOperation getPrePostOperation(Operation operation) {
        return preOperationMap.get(operation);
    }

    public Expression peekLastExp() {
        return getExpressionQueue().peekLast();
    }


    public void clearAll() {
        getExpressionQueue().clear();
        setCurrentParentExp(null);
        setCurrentExp(null);
    }

    public void closePreOper(Expression expression) {
        expression.getLastPreOper().setOpen(false);
        resetParent(expression);
    }

    public void resetCurrentParent() {
        resetParent(getCurrentParentExp());
    }

    public void resetParent(Expression parent) {
        if (parent == null) {
            return;
        }
        // set parent's parent as currentParent if present (set previous parent)
        if (parent.hasParent()) {
            setCurrentParentExp(parent.getParent());
        }
        else {
            setCurrentParentExp(null);
            getExpressionQueue().remove(parent);
        }
    }

    public Optional<Expression> getCurrentOrParent() {
        return hasCurrent() ? Optional.ofNullable(getCurrentExp()) : Optional.ofNullable(getCurrentParentExp());
//        Expression current = null;
        /*if (hasCurrent()) {
            current = getCurrentExp();
        }
        else if (hasCurrentParent()) {
//            current = getCurrentParentExp().peekLastChildOrSelf();
            current = getCurrentParentExp();
        }
        return current;*/
    }

    public boolean removeExpression(Expression expression) {
        return getExpressionQueue().remove(expression);
    }

    public void removeLastPreOperAndResetParent(Expression current) {
        current.removeLastPreOper();
        if ( ! current.hasPreOperations()) {
            resetCurrentParent();
//            manager.setCurrentParentExp(null);
        }
    }

    public void removeLastDigitAndResetCurrent(Expression current) {
        current.removeLastDigit();

        // expression is empty (valid for deletetion)
        if ( ! current.hasValue()) {
            // try to remove from parent
            if (current.detachFromParent()) {
                Expression parent = current.getParent();
                if (parent.hasChildren()) {
                    // if removed then set current's parent next (last) child
                    setCurrentExp(parent.peekLastChild());
                }
                else {
                    setCurrentParentExp(parent);
                }
            }
            else {
                setCurrentParentExp(null);
            }
            setCurrentExp(null);
        }
    }

    public boolean hasExpressions() {
        return ! getExpressionQueue().isEmpty();
    }

}
