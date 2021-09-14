package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class SqrtPreOperation extends AbstractPrePostOperation {

    public SqrtPreOperation() {
        super(
                OperatorText.ROOT + OperatorText.PARENTHESES_LEFT,
                OperatorText.PARENTHESES_RIGHT,
                "",
                "");
        isOpen = true;
    }

    @Override
    public double apply(double value) {
        return 0;
    }

    @Override
    public boolean isClosable() {
        return true;
    }
}
