package com.emrissol.expression.operation;

import com.emrissol.expression.OperatorText;

public class SqrtOperation extends AbstractPrePostOperation {

    public SqrtOperation() {
        super(
                OperatorText.ROOT + OperatorText.PARENTHESES_LEFT,
                OperatorText.PARENTHESES_RIGHT,
                "",
                "",
                Operation.SQRT);
        isOpen = true;
    }

    @Override
    public Number apply(String thisValue) {
        return 0;
    }
}
