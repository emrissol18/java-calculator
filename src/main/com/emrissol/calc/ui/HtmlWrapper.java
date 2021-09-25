package com.emrissol.calc.ui;

import lombok.NonNull;

public class HtmlWrapper {

    public static String fontWithStyle(String value, @NonNull String options) {
        return "<font ".concat(options).concat(">").concat(value).concat("</font>");
    }

    public static String fontGrey(String value) {
        return "<font color='gray'>".concat(value).concat("</font>");
    }

}
