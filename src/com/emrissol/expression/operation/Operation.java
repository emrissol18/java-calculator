package com.emrissol.expression.operation;

import com.emrissol.expression.OperatorText;
import lombok.Getter;

public enum  Operation {
    ADD(OperatorText.SIGN_PLUS),
    SUBSTRUCT(OperatorText.SIGN_MINUS),
    DIVIDE(OperatorText.SIGN_DIVIDE),
    MULTIPLY(OperatorText.SIGN_MULTIPLY),
    SQRT(OperatorText.ROOT + OperatorText.PARENTHESES_LEFT),
    NEGATIVE(OperatorText.NEGATIVE),
    POINT("."),
    DEL(OperatorText.DEL),
    PERCENT(OperatorText.PERCENT),
    CLEAR(OperatorText.CLEAR),
    EQUALS(OperatorText.EQUAL);

    @Getter
    private String text;

    Operation(String text) {
        this.text = text;
    }
    //    , SUB_EXP_START //parenthesis open
//    , SUB_EXP_END   //parenthesis close


}
