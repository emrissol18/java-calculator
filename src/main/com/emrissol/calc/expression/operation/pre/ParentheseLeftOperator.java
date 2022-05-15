package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.operation.layout.OperatorConsts;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class ParentheseLeftOperator extends AbstractPrePostOperation {

    public ParentheseLeftOperator() {
        super(OperatorConsts.PARENTHESES_LEFT, OperatorConsts.PARENTHESES_RIGHT,
                "<span style='line-height:40px;'>", "</span>", true);
    }

    @Override
    public double apply(double value) {
        return value;
    }

    @Override
    public boolean isClosable() {
        return true;
    }

}
