package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.operation.layout.OperatorConsts;
import com.emrissol.calc.expression.operation.layout.SpecialOperatorConsts;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class SqrtPreOperation extends AbstractPrePostOperation {

    public SqrtPreOperation() {
        super(SpecialOperatorConsts.SQRT + OperatorConsts.PARENTHESES_LEFT,
                OperatorConsts.PARENTHESES_RIGHT,
                "", "", true);
    }

    @Override
    public double apply(double value) {
        return Math.sqrt(value);
    }

    @Override
    public boolean isClosable() {
        return true;
    }

}
