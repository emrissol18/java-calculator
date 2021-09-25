package com.emrissol.calc.ui;

import lombok.NonNull;

public class HtmlStringUtil {

    public static String fontWithStyle(String value, @NonNull String options) {
        return "<font ".concat(options).concat(">").concat(value).concat("</font>");
    }

    public static String fontGrey(String value) {
        return "<font color='gray'>".concat(value).concat("</font>");
    }

    public static String removeLastChar(String value) {
        if ( ! value.isEmpty()) {
            return value.substring(0, value.length() - 1);
        }
        else {
            System.out.println("removeLastDigit(): has no value");
        }
        return value;
    }
}
