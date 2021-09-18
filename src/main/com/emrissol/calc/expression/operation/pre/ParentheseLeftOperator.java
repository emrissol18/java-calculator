package com.emrissol.calc.expression.operation.pre;

import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;

public class ParentheseLeftOperator extends AbstractPrePostOperation {

    public ParentheseLeftOperator() {
        super(OperatorText.PARENTHESES_LEFT, OperatorText.PARENTHESES_RIGHT,
                "<span style='line-height:40px;'>", "</span>", true);
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
