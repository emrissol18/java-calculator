package com.emrissol;

import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

// singleton
public class Manager {

    private static Manager instance;

    private Manager() {
        for (Operation operation : EnumSet.allOf(Operation.class)) {
            operations.put(operation.getText(), operation);
        }
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    @Getter
//    private Set<Operation> operations = EnumSet.allOf(Operation.class);
    private Map<String, Operation> operations = new HashMap<>();

    @Getter
    @Setter
    private Operation operationAcc;

    @Getter
    @Setter
    private Expression currentExp = null;

    @Getter
    private Queue<Expression> expressions = new LinkedList<>();

    public void addExpressionIfHasNoParent(Expression expression) {
        if (expression.getParent() == null) {
            expressions.add(expression);
        }
    }


    public Operation getOperation(String text) {
        return operations.get(text);
    }

    public boolean hasCurrent() {
        return currentExp != null;
    }

    public void addToValueOfCurrent(String text) {
        currentExp.setValue(currentExp.getValue() + text);
    }
    public boolean currentHasPreOper() {
        return currentExp.hasPreOperation();
    }

    public String getCurrentValue() {
        return currentExp.getValue();
    }
}
