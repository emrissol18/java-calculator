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
        return "<sup>".concat(
                HtmlStringUtil.fontWithStyle(super.getConcatEnd(), "size=4")
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
