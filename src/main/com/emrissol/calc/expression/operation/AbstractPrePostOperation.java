package com.emrissol.calc.expression.operation;

import com.emrissol.calc.ui.HtmlStringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public abstract class AbstractPrePostOperation {

    protected String textStart;
    protected String textEnd;
    protected String htmlStart;
    protected String htmlEnd;
    protected boolean isOpen;
    protected String value;

    public abstract double apply(double value);

    public abstract boolean isClosable();

    public AbstractPrePostOperation(String textStart, String textEnd,
                                    String htmlStart, String htmlEnd) {
        this.textStart = textStart;
        this.textEnd = textEnd;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
    }

    public AbstractPrePostOperation(String textStart, String textEnd,
                                    String htmlStart, String htmlEnd, boolean isOpen) {
        this(textStart, textEnd, htmlStart, htmlEnd);
        this.isOpen = isOpen;
    }

    public AbstractPrePostOperation(String value) {
        this.textStart = "";
        this.textEnd = "";
        this.htmlStart = "";
        this.htmlEnd = "";
        this.value = value;
    }

    public String getHtmlEnd() {
        return htmlEnd;
    }

    // highlight textEnd by default (for root, cos and sin etc.)
    public String getTextEnd() {
        if (isOpen) {
            return HtmlStringUtil.fontGrey(textEnd);
        }
        return textEnd;
    }

    public String getConcatEnd() {
        String value = isValueAvailable() ? getValue() : "";
        return value.concat(getTextEnd()).concat(getHtmlEnd());
    }

    public boolean isValueAvailable() {
        return value != null;
    }

    public boolean hasValue() {
        return ! value.isEmpty() && ! value.isBlank();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        return getClass().equals(object.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(textStart, textEnd, isOpen);
    }
}
