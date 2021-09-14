package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class NegativePreOperation extends AbstractPrePostOperation {

    public NegativePreOperation() {
        super(OperatorText.PARENTHESES_LEFT.concat(OperatorText.NEGATIVE_LAYOUT), OperatorText.PARENTHESES_RIGHT, "", "");
    }

    @Override
    public double apply(double value) {
        return 0;
    }

    @Override
    public boolean isClosable() {
        return false;
    }
}
