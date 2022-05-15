package com.emrissol.calc.expression.operation.post;

import com.emrissol.calc.expression.operation.layout.SpecialOperatorConsts;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class FactorialPostOperation extends AbstractPrePostOperation {

    public FactorialPostOperation() {
        super("", SpecialOperatorConsts.FACTORIAL, "", "");
    }

    @Override
    public double apply(double value) {
        return factorial(value);
    }

    @Override
    public boolean isClosable() {
        return false;
    }

    private double factorial(double v) {
        if (v < 2 && v >= 0) {
            return 1;
        }
        double v1 = v;
        while (v > 1) {
            v1 *= (--v);
        }
        return v1;
    }
}
