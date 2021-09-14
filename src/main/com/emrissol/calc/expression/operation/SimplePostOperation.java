package com.emrissol.calc.expression.operation;

import com.emrissol.calc.expression.OperatorText;
import lombok.Getter;

public enum SimplePostOperation {

    ADD(OperatorText.SIGN_PLUS),
    SUBSTRUCT(OperatorText.SIGN_MINUS),
    DIVIDE(OperatorText.SIGN_DIVIDE),
    MULTIPLY(OperatorText.SIGN_MULTIPLY),

    POINT(OperatorText.POINT),
    EQUALS(OperatorText.EQUAL);

    @Getter
    private String text;

    SimplePostOperation(String text) {
        this.text = text;
    }
    //    , SUB_EXP_START //parenthesis open
//    , SUB_EXP_END   //parenthesis close


}
