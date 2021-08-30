package com.emrissol;

import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.expression.operation.AbstractPrePostOperation;
import com.emrissol.expression.operation.SqrtOperation;
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

    public void addExpressionIfHasNoParent(Expression expression) {
        if (expression.getParent() == null && ! expressionQueue.contains(expression)) {
            expressionQueue.add(expression);
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

    public Expression pollLastExp() {
        return getExpressionQueue().pollLast();
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
        }
        setCurrentExp(parent);
    }

    public Expression getCurrentOrParent() {
        Expression current = null;
        if (hasCurrent()) {
            current = getCurrentExp();
        }
        else if (hasCurrentParent()) {
            current = getCurrentParentExp();
        }
        return current;
    }

}
