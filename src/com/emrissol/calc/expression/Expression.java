package com.emrissol.calc.expression;

import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.expression.operation.Operation;
import com.emrissol.calc.log.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Expression {

    private static final Logger logger = new Logger(Expression.class);
    static short ID = 0;
    protected short id;

    private Double numberValue;

    protected String value = "";
//    protected PreOperation preOperation;
    protected Operation operation;

    // how much chars expression takes in context
    @Getter
    private short length = 0;

    @Getter(AccessLevel.NONE)
    protected Deque<AbstractPrePostOperation> preOperations;
    @Getter(AccessLevel.NONE)
    protected Deque<AbstractPrePostOperation> postOperations;

    protected Expression parent;

    @Getter(AccessLevel.NONE)
    protected Deque<Expression> children;

    public Expression() {
        this.id = ID++;
    }

    public Expression(String value) {
        this();
        this.value = value;
    }

    public Expression(String value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public static void resetID() {
        ID = 0;
    }
    public boolean hasValue() {
        return ! getValue().isEmpty();
    }

    public boolean hasPoint() {
        return hasValue() && (getValue().contains(Operation.POINT.getText()));
    }

    public boolean hasOperation() {
        return operation != null;
    }

    public boolean hasPreOperations() {
        return preOperations != null && ! getPreOperations().isEmpty();
    }

    public Deque<AbstractPrePostOperation> getPreOperations() {
        if (preOperations == null) {
            preOperations = new LinkedList<>();
        }
        return preOperations;
    }

    public void setLastPreOperOpen(boolean isOpen) {
        getPreOperations().peekLast().setOpen(isOpen);
    }

    public AbstractPrePostOperation getLastPreOper() {
        return getPreOperations().peekLast();
    }

    public boolean isLastPreOperClosed() {
        if ( ! hasPreOperations()) {
            return true;
        }
        return ! isLastPreOperOpen();
    }
    public boolean isLastPreOperOpen() {
        return getLastPreOper().isOpen();
    }

    public Deque<AbstractPrePostOperation> getPostOperations() {
        if (postOperations == null) {
            postOperations = new LinkedList<>();
        }
        return postOperations;
    }

    public AbstractPrePostOperation getLastPostOper() {
        return getPostOperations().getLast();
    }

    public boolean hasPostOperations() {
        return postOperations != null && ! getPostOperations().isEmpty();
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Deque<Expression> getChildren() {
        if (children == null) {
            children = new LinkedList<>();
        }
        return children;
    }

    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    public boolean detachFromParent() {
        if (hasParent()) {
            getParent().removeChild(this);
            return true;
        }
        return false;
    }

    public void addExpression(Expression expression) {
        expression.setParent(this);
        getChildren().add(expression);
//        System.err.println("ADDING CHILDREN " + expression.getId() + " TO PARENT " + getId());
    }

    public String getLayout() {
//        System.out.println("getLayout() of "+id);
        StringBuilder stringBuilder = new StringBuilder();
        if (hasChildren()) {
            getChildren().forEach( e -> {
                stringBuilder.append(e.getLayout());
            });
        }

        String thisValue = getValue();
//        if ( hasOperation() && ! getOperation().equals(Operation.SQRT)) {
//            thisValue = thisValue.concat(getOperation().getText());
//        }
        stringBuilder.append(thisValue);
//        System.err.println("thisValue = " + thisValue);

        StringBuilder chunkStart;
        StringBuilder chunkEnd;
        boolean operationAppended = false;

        if (hasPreOperations() || hasPostOperations()) {
            chunkStart = new StringBuilder();
            chunkEnd = new StringBuilder();

            // PRE OPERATIONS
            if (hasPreOperations()) {
                getPreOperations().forEach( e -> {
                    chunkStart.append(e.getHtmlStart().concat(e.getTextStart()));
                    chunkEnd.insert(0, e.getConcatEnd());
//                    chunkEnd.insert(0, e.getConcatEnd().concat(getOperationText()));
                });
            }

//            chunkStart.append(thisValue);
            // POST OPERATIONS
            if (hasPostOperations()) {
                getPostOperations().forEach( e -> {
                    chunkStart.append(e.getHtmlStart().concat(e.getTextStart()));
                    chunkEnd.insert(0, e.getConcatEnd());
                });
            }

            // apend post operator (sign)
            chunkEnd.append(getOperationText());
            stringBuilder
                    .insert(0, chunkStart.toString())
                    .append(chunkEnd.toString());
        }
        else {
            stringBuilder.append(getOperationText());
        }
        length = (short) stringBuilder.length();
        return stringBuilder.toString();
    }

    private String getOperationText() {
        if (hasOperation())
            return getOperation().getText();
        return "";
    }

    // find most top parent in expression hierarchy tree
    public Expression getAncestorParentOrSelf() {
        if (hasParent()) {
            Expression parent = getParent();
            while (parent.hasParent()) {
                parent = parent.getParent();
            }
            return parent;
        }
        return this;
    }

    /**
     * Get most last expression (most lower) in expression hierarchy,<br>
     * i.e.: this -> last_child -> last_child...<br/>
     * or itself.
     * @return Expression object
     */
    public Expression getDescendantChildOrItself() {
        if (hasChildren()) {
            Expression child = peekLastChild();
            while (child.hasChildren()) {
                child = child.peekLastChild();
            }
            return child;
        }
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Expression that = (Expression) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, operation);
    }

    @Override
    public String toString() {
        /*StringBuilder stringBuilder = new StringBuilder("");
        if ( ! getChildren().isEmpty()) {
            stringBuilder.setLength(0);
            for (Expression expression : getChildren()) {
                stringBuilder.append("id=").append(id).append(", value=").append(value).append(", operation=").append(operation).append(", preOperation =").append(preOperation);
            }
        }*/
        return "\n=====================================\nExpression#"+id+"{" +
                ", parentId =" + (parent == null ? "null" : parent.getId()) +
                ", value=" + value +
                ", length=" + length +
                ", operation=" + operation +
//                ", preOperation =" + preOperation +
                ",\npreOperations =" + (getPreOperations().isEmpty() ? "empty" :
                "\n\t\t" + getPreOperations().stream().map(AbstractPrePostOperation::toString).collect(Collectors.joining("\n\t\t"))
        ) +
                ",\npostOperations =" + (getPostOperations().isEmpty() ? "empty" :
                "\n\t\t" + getPostOperations().stream().map(AbstractPrePostOperation::toString).collect(Collectors.joining("\n\t\t"))
        ) +
//                "\n\t\texpressions =" + stringBuilder.toString() +
                ",\nexpressions =" +
                (getChildren().isEmpty() ? "empty" :
                        "\n\t\t" + getChildren().stream().map(Expression::toString).collect(Collectors.joining("\n\t\t"))
                ) +
                '}';
    }


    public boolean removeLastDigitAndIsEmpty() {
        removeLastDigit();
        if ( ! hasValue()) {
            return true;
        }
        return false;
    }

    public void removeLastDigit() {
        if (hasValue()) {
            setValue( value.substring(0, value.length() - 1) );
        }
        else {
            logger.logError("removeLastDigit(): has no value");
        }
    }

    public Expression peekLastChild() {
        return getChildren().peekLast();
    }

    public boolean lastChildHasOperation() {
        return hasChildren() && peekLastChild().hasOperation();
    }
    public Expression peekLastChildOrSelf() {
        if (hasOperation() || ! hasChildren()) {
            return this;
        }
        return peekLastChild();
    }

    public void removeChild(Expression expression) {
        getChildren().remove(expression);
    }

    public void removeLastChild() {
        removeChild(peekLastChild());
    }

    public void removeLastPreOper() {
        getPreOperations().pollLast();
    }

    public Expression getParentOrSelf() {
        return hasParent() ? getParent() : this;
    }

    public boolean isParent() {
//        return hasPreOperations() || hasPostOperations() || hasChildren();
        return hasPreOperations() || hasChildren();
    }

    public void addToValue(String value) {
        setValue(getValue() + value);
    }

    public boolean isNegative() {
//        return value.startsWith(Operation.NEGATIVE.getText());
        return hasValue() && value.charAt(0) == Operation.NEGATIVE.getText().charAt(0);
    }

    public void toggleNegative() {
        String negativeLayout = OperatorText.NEGATIVE_LAYOUT;
        if ( isNegative()) {
            setValue(value.replaceFirst(negativeLayout, ""));
        }
        else {
            setValue(negativeLayout.concat(getValue()));
        }
    }

    public void parseValue() {
        if (value.isEmpty() || numberValue != null) {
            numberValue = 0.d;
            return;
        }
        try {
            numberValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
