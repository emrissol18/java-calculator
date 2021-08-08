package com.emrissol;

import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.expression.operation.PreOperation;
import com.emrissol.expression.operation.SqrtOperation;
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
        preOperationMap.put(Operation.SQRT, new SqrtOperation());
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
    private Expression currentParentExp = null;

    @Getter
    @Setter
    private Expression currentExp = null;

    @Getter
    private Deque<Expression> expressionQueue = new LinkedList<>();

    private Map<Operation, PreOperation> preOperationMap = new HashMap<>();

    public void addExpressionIfHasNoParent(Expression expression) {
        if (expression.getParent() == null && ! expressionQueue.contains(expression)) {
            expressionQueue.add(expression);
        }
    }

    public Operation getOperation(String text) {
        return operations.get(text);
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

    public void addToValueOfCurrentParent(String text) {
        currentParentExp.setValue(currentParentExp.getValue() + text);
    }

    public boolean currentHasPreOper() {
        return currentExp.hasPreOperation();
    }

    public String getCurrentValue() {
        return currentExp.getValue();
    }


    public PreOperation getPreOperation(Operation operation) {
        return preOperationMap.get(operation);
    }
}
