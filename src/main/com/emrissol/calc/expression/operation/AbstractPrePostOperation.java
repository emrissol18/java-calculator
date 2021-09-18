package com.emrissol.calc.expression.operation;

import com.emrissol.calc.ui.HtmlStringUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
public abstract class AbstractPrePostOperation {

    protected String textStart;
    protected String textEnd;
    // as option make as placeholder instead. e.g. <tag>{$text}</tag>
    protected String htmlStart;
    @Getter(AccessLevel.NONE)
    protected String htmlEnd;
    protected boolean isOpen;

    protected String value;

//    protected String value; // digits as string
//    protected Operation operation;


    public AbstractPrePostOperation(String textStart, String textEnd, String htmlStart, String htmlEnd) {
        this.textStart = textStart;
        this.textEnd = textEnd;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
    }

    public AbstractPrePostOperation(String textStart, String textEnd, String htmlStart, String htmlEnd, @NonNull String value) {
        this(textStart, textEnd, htmlStart, htmlEnd);
        this.value = value;
    }

    public AbstractPrePostOperation(String textStart, String textEnd, String htmlStart, String htmlEnd, boolean isOpen) {
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

    //    public abstract String getTextStart();
//    public abstract String getTextEnd();
//    public abstract String getValue();
//    public abstract Operation getOperation();

    public abstract double apply(double value);

    public boolean isValueAvailable() {
        return value != null;
    }

    public boolean hasValue() {
        return ! value.isEmpty() && ! value.isBlank();
    }

    public abstract boolean isClosable();
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "textStart='" + textStart + '\'' +
                ", textEnd='" + textEnd + '\'' +
                ", htmlStart='" + htmlStart + '\'' +
                ", htmlEnd='" + htmlEnd + '\'' +
                ", isOpen='" + isOpen + '\'' +
                ", value='" + value + '\'' +
//                ", operation=" + operation +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass().equals(object.getClass())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen);
    }
}
