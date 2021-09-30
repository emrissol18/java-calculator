package com.emrissol.calc;

import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.expression.operation.post.PowPostOperation;

public class TestItemsFactory {

    public Expression createExp(double value, SimplePostOperation operation) {
        Expression expression = new Expression("", operation);
        expression.setNumberValue(value);
        return expression;
    }

    public Expression createExp(double value) {
        Expression expression = new Expression();
        expression.setNumberValue(value);
        return expression;
    }

    public PowPostOperation createPow(String value) {
        PowPostOperation powPostOperation = new PowPostOperation();
        powPostOperation.setValue(value);
        return powPostOperation;
    }
}
