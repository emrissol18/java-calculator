package com.emrissol.expression.operation;

import com.emrissol.expression.OperatorText;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
    protected Operation operation;

    public AbstractPrePostOperation(String textStart, String textEnd, String htmlStart, String htmlEnd, Operation operation) {
        this.textStart = textStart;
        this.textEnd = textEnd;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
//        this.value = value;
        this.operation = operation;
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

    public abstract Number apply(String thisValue);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "textStart='" + textStart + '\'' +
                ", textEnd='" + textEnd + '\'' +
                ", htmlStart='" + htmlStart + '\'' +
                ", htmlEnd='" + htmlEnd + '\'' +
                ", isOpen='" + isOpen + '\'' +
//                ", value='" + value + '\'' +
                ", operation=" + operation +
                '}';
    }
}
