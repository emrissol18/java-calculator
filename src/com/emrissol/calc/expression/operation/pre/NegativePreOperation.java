package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class NegativePreOperation extends AbstractPrePostOperation {

    public NegativePreOperation() {
        super(OperatorText.NEGATIVE_LAYOUT, "", "", "");
    }

    @Override
    public double apply(double value) {
        return value * (-1);
    }
}
