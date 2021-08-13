package com.emrissol.expression.operation;

import com.emrissol.expression.LabelText;
import lombok.Getter;

public enum  Operation {
    ADD(LabelText.SIGN_PLUS),
    SUBSTRUCT(LabelText.SIGN_MINUS),
    DIVIDE(LabelText.SIGN_DIVIDE),
    MULTIPLY(LabelText.SIGN_MULTIPLY),
    SQRT(LabelText.ROOT + LabelText.PARENTHESIS_LEFT),
    NEGATIVE(LabelText.NEGATIVE),
    POINT("."),
    DEL(LabelText.DEL),
    PERCENT(LabelText.PERCENT),
    CLEAR(LabelText.CLEAR),
    EQUALS(LabelText.EQUAL);

    @Getter
    private String text;

    Operation(String text) {
        this.text = text;
    }
    //    , SUB_EXP_START //parenthesis open
//    , SUB_EXP_END   //parenthesis close


}
