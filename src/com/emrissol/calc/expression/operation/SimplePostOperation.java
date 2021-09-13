package com.emrissol.calc.expression.operation;

import com.emrissol.calc.expression.OperatorText;
import lombok.Getter;

public enum SimplePostOperation {

    ADD(OperatorText.SIGN_PLUS),
    SUBSTRUCT(OperatorText.SIGN_MINUS),
    DIVIDE(OperatorText.SIGN_DIVIDE),
    MULTIPLY(OperatorText.SIGN_MULTIPLY),

    POINT("."),
    EQUALS(OperatorText.EQUAL),
    DEL(OperatorText.DEL),
    CLEAR(OperatorText.CLEAR),

    SQRT(OperatorText.ROOT + OperatorText.PARENTHESES_LEFT),
    NEGATIVE(OperatorText.NEGATIVE),
    PERCENT(OperatorText.PERCENT),
    FACTORIAL(OperatorText.FACTORIAL);

    @Getter
    private String text;

    SimplePostOperation(String text) {
        this.text = text;
    }
    //    , SUB_EXP_START //parenthesis open
//    , SUB_EXP_END   //parenthesis close


}
