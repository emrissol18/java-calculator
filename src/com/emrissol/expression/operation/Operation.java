package com.emrissol.expression.operation;

import lombok.Getter;

public enum  Operation {
    ADD("+"),
    SUBSTRUCT("—"),
    DIVIDE("/"),
    MULTIPLY("*"),
    SQRT("root("),
    NEGATIVE("+/-"),
    POINT("."),
    DEL("del"),
    PERCENT("%"),
    CLEAR("CA"),
    EQUALS("=");

    @Getter
    private String text;

    Operation(String text) {
        this.text = text;
    }
    //    , SUB_EXP_START //parenthesis open
//    , SUB_EXP_END   //parenthesis close


}
