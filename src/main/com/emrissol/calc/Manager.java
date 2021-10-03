package com.emrissol.calc;

import com.emrissol.calc.expression.Expression;
import lombok.Getter;
import lombok.NonNull;
import java.util.Deque;
import java.util.LinkedList;

// singleton
public class Manager {

    private static Manager instance;

    private Manager() {
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    @Getter
    private Expression currentExp = null;

    @Getter
    private Deque<Expression> expressionQueue = new LinkedList<>();

    public void addExpression(Expression expression) {
        getExpressionQueue().add(expression);
    }

    public void addOrphanExpression(Expression expression) {
        if ( ! expression.hasParent() && ! expressionQueue.contains(expression)) {
            addExpression(expression);
        }
    }

    public void setCurrentExp(Expression newCurrent) {
        // if current not null
        if ( hasCurrent()
                &&
                // AND is newCurrent is parent of current
                ( newCurrent == null || (currentExp.hasParent() && currentExp.getParent().equals(newCurrent)))) {

            // parse current's value if newCurrent == null OR right after its parent is closed
            currentExp.parseValue();
        }
        this.currentExp = newCurrent;
    }

    /**
     * Add non null newExpression to current if present or add to global list and set newExpression as new current.
     * @param newExpression expression
     */
    public void setAndAddCurrentExp(@NonNull Expression newExpression) {
        if (hasCurrent()) {
            getCurrentExp().addExpression(newExpression);
        }
        else {
            addOrphanExpression(newExpression);
        }
        setCurrentExp(newExpression);
    }

    public boolean hasCurrent() {
        return currentExp != null;
    }

    public Expression peekLastExp() {
        return getExpressionQueue().peekLast();
    }

    public void clearAll() {
        getExpressionQueue().clear();
        currentExp = null;
        Expression.resetID();
    }

    public boolean removeExpression(Expression expression) {
        return getExpressionQueue().remove(expression);
    }

    public boolean hasExpressions() {
        return ! getExpressionQueue().isEmpty();
    }

}
