package com.emrissol.calc.expression.operation.post;

import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.ui.HtmlStringUtil;

public class PowPostOperation extends AbstractPrePostOperation {

    public PowPostOperation() {
        super("");
    }

    @Override
    public void setValue(String value) {
        if ( ! value.isBlank() && value.charAt(0) == ' ') {
            value = value.trim();
        }
        super.setValue(value);
    }

    @Override
    public String getConcatEnd() {
//        return "<sup><font size=+1 style='padding:0px;background-color:#cdcdcd;'>".concat(super.getConcatEnd()).concat("</font></sup>");
//        return "<sup><font size=-.8>".concat(super.getConcatEnd()).concat("</font></sup>");
//        String style = "";
//        if ( ! hasValue()) {
//            style = " style='background-color:#bcbcbc;'";
//            setValue(" ");
//        }
        return "<sup>".concat(
                HtmlStringUtil.fontWithStyle(super.getConcatEnd(), "size=-.6")
        ).concat("</sup>");
    }

    @Override
    public double apply(double value) {
        int pow = Integer.parseInt(this.value);
        return Math.pow(value, pow);
    }

    @Override
    public boolean isClosable() {
        return false;
    }
}
