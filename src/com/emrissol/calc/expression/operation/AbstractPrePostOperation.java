package com.emrissol.calc.expression.operation;

import com.emrissol.calc.expression.OperatorText;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
public abstract class AbstractPrePostOperation {

    protected String textStart;
    protected String textEnd;
    protected String htmlStart;
    @Getter(AccessLevel.NONE)
    protected String htmlEnd;
    protected boolean isOpen;
//    protected String value; // digits as string
//    protected Operation operation;

    public AbstractPrePostOperation(String textStart, String textEnd, String htmlStart, String htmlEnd) {
        this.textStart = textStart;
        this.textEnd = textEnd;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
//        this.value = value;
//        this.operation = operation;
    }

    public String getHtmlEnd() {
        return htmlEnd;
    }

    // highlight textEnd by default (for root, cos and sin etc.)
    public String getTextEnd() {
        if (isOpen) {
            return OperatorText.highlight(textEnd);
        }
        return textEnd;
    }

    public String getConcatEnd() {
        return getTextEnd().concat(getHtmlEnd());
    }

    //    public abstract String getTextStart();
//    public abstract String getTextEnd();
//    public abstract String getValue();
//    public abstract Operation getOperation();

    public abstract double apply(double value);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "textStart='" + textStart + '\'' +
                ", textEnd='" + textEnd + '\'' +
                ", htmlStart='" + htmlStart + '\'' +
                ", htmlEnd='" + htmlEnd + '\'' +
                ", isOpen='" + isOpen + '\'' +
//                ", value='" + value + '\'' +
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
