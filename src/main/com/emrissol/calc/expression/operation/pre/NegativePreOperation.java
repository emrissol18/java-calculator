package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.operation.layout.OperatorConsts;
import com.emrissol.calc.expression.operation.layout.SimpleOperatorConsts;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class NegativePreOperation extends AbstractPrePostOperation {

    public NegativePreOperation() {
        super(OperatorConsts.PARENTHESES_LEFT.concat(SimpleOperatorConsts.NEGATIVE_LAYOUT),
                OperatorConsts.PARENTHESES_RIGHT, "", "");
    }

    @Override
    public double apply(double value) {
        return -value;
    }

    @Override
    public boolean isClosable() {
        return false;
    }

}
