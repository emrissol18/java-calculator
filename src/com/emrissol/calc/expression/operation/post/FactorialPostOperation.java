package com.emrissol.calc.expression.operation.post;

import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class FactorialPostOperation extends AbstractPrePostOperation {

    public FactorialPostOperation() {
        super("", OperatorText.FACTORIAL, "", "");
    }

    @Override
    public double apply(double value) {
        return 0;
    }
}
