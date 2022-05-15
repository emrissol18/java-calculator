package com.emrissol.calc.expression.operation;

import com.emrissol.calc.expression.operation.layout.SimpleOperatorConsts;
import lombok.Getter;

public enum SimplePostOperation {

    ADD(SimpleOperatorConsts.SIGN_PLUS),
    SUBSTRUCT(SimpleOperatorConsts.SIGN_MINUS),
    DIVIDE(SimpleOperatorConsts.SIGN_DIVIDE),
    MULTIPLY(SimpleOperatorConsts.SIGN_MULTIPLY),
    POINT(SimpleOperatorConsts.POINT),
    EQUALS(SimpleOperatorConsts.EQUAL);

    @Getter
    private String text;

    SimplePostOperation(String text) {
        this.text = text;
    }

}
