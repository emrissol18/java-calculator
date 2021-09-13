package com.emrissol.calc.expression;

public class OperatorText {
    public static final String ROOT = "root";
//    public static final String NEGATIVE = "+/-";
    public static final String NEGATIVE = "\u00B1"; // +/-
    public static final String NEGATIVE_LAYOUT = "-"; // +/-
    public static final String PARENTHESES_LEFT = "(";
    public static final String PARENTHESES_RIGHT = ")";
    public static final String PERCENT = "%";
    public static final String CLEAR = "C";
    public static final String DEL = "del";
    public static final String SIGN_DIVIDE = "\u00F7";
    public static final String SIGN_MULTIPLY = "\u00D7";
    public static final String SIGN_PLUS = "\u002B";
    public static final String SIGN_MINUS = "\u2212";
    public static final String EQUAL = "=";
    public static final String POINT = ".";

    public static String highlight(String value) {
        return "<font color='gray'>" + value + "</font>";
    }
}
