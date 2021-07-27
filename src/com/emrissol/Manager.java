package com.emrissol;

import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.util.*;

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
    private Set<Operation> operations = EnumSet.allOf(Operation.class);

    @Getter
    @Setter
    private String valueAcc = "";

    @Getter
    @Setter
    private Operation operationAcc;

    @Getter
    @Setter
    private Expression currentExp = null;

    @Getter
    private Queue<Expression> expressions = new LinkedList<>();

    public void addExpression(Expression expression) {
        expressions.add(expression);
    }


    public Operation getOperation(JButton target) {
        for (Operation operation : operations) {
            if (operation.toString().equals(target.getName())) {
                return operation;
            }
        }
        return null;
    }

    public void addToValueAcc(String digit) {
        valueAcc += digit;
    }

}
