package com.emrissol.calc.expression;

import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.expression.operation.post.FactorialPostOperation;
import com.emrissol.calc.log.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Expression {

    private static final Logger logger = new Logger(Expression.class);
    static short ID = 0;
    protected short id;

    private double numberValue;

    protected String value = "";
//    protected PreOperation preOperation;
    protected SimplePostOperation operation;

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

    public Expression(String value, SimplePostOperation operation) {
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
        return hasValue() && (getValue().contains(SimplePostOperation.POINT.getText()));
    }

    public boolean hasOperation() {
        return operation != null;
    }

    public boolean hasPreOperations() {
        return preOperations != null && ! getPreOperations().isEmpty();
    }

    public boolean hasClosablePreOperations() {
        if ( ! hasPreOperations() ) {
            return false;
        }
        for (AbstractPrePostOperation o : getPreOperations()) {
            if (o.isClosable()) {
                return true;
            }
        }
        return false;
    }

    public Deque<AbstractPrePostOperation> getPreOperations() {
        if (preOperations == null) {
            preOperations = new LinkedList<>();
        }
        return preOperations;
    }

    public void setLastPreOperOpen(boolean isOpen) {
//        getPreOperations().peekLast().setOpen(isOpen);
        getLastClosablePreOper().setOpen(isOpen);
    }

    public boolean isLastPreOperClosable() {
        return hasPreOperations() && getPreOperations().peekLast().isClosable();
    }

    public AbstractPrePostOperation getLastClosablePreOper() {
        AbstractPrePostOperation last = getPreOperations().peekLast();
        if ( ! last.isClosable()) {
            Iterator<AbstractPrePostOperation> iterator = getPreOperations().descendingIterator();
            iterator.next(); // skip last
            while ( iterator.hasNext() && ! last.isClosable()) {
                last = iterator.next();
            }
        }
        return last;
    }

    public boolean isLastPreOperClosed() {
        if ( ! hasPreOperations()) {
            return true;
        }
//        return getLastPreOper().isClosable() && ! isLastPreOperOpen();
        return ! getLastClosablePreOper().isOpen();
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
        StringBuilder stringBuilder = new StringBuilder();
        if (hasChildren()) {
            getChildren().forEach( e -> {
                stringBuilder.append(e.getLayout());
            });
        }

        String thisValue = getValue();
        stringBuilder.append(thisValue);

        StringBuilder chunkStart;
        StringBuilder chunkEnd;
        boolean operationAppended = false;

        if (hasPreOperations() || hasPostOperations()) {
            chunkStart = new StringBuilder();
            chunkEnd = new StringBuilder();

            // PRE OPERATIONS
            if (hasPreOperations()) {
                getPreOperations().forEach( e -> {
                    // append to left
                    chunkStart.insert(0, e.getHtmlStart().concat(e.getTextStart()));
                    // append to right
                    chunkEnd.append(e.getConcatEnd());
                });
            }

            // POST OPERATIONS
            if (hasPostOperations()) {
                getPostOperations().forEach( e -> {
                    // append to left
                    chunkStart.insert(0, e.getHtmlStart().concat(e.getTextStart()));
                    // append to right
                    chunkEnd.append(e.getConcatEnd());
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

    /**
     * Find most top parent (most upper) in expression hierarchy,<br>
     * i.e.: this -> parent -> parent...<br/>
     * or itself.
     * @return Expression object
     */
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
     * Find most last expression (most lower) in expression hierarchy if its parent does not have post operations,<br>
     * i.e.: this -> last_child -> last_child...<br/>
     * or itself.
     * @return Expression object
     */
    public Expression getDescendantChildOrItself() {
        if (hasPostOperations()) {
            return this;
        }
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
        return (hasClosablePreOperations() && getPreOperations().stream().anyMatch( o -> o.isClosable())) || hasChildren();
    }

    public void addToValue(String value) {
        setValue(getValue() + value);
    }

    public boolean isNegative() {
//        return value.startsWith(Operation.NEGATIVE.getText());
        return hasValue() && value.charAt(0) == OperatorText.NEGATIVE.charAt(0);
    }
    public boolean isEffectivelyNegative() {
        return isNegative() && getValue().length() == 1;
    }

    public void toggleNegativeOld() {
        String negativeLayout = OperatorText.NEGATIVE_LAYOUT;
        if ( isNegative()) {
            setValue(value.replaceFirst(negativeLayout, ""));
        }
        else {
            setValue(negativeLayout.concat(getValue()));
        }
    }

    public void parseValue() {
//        logger.log("parsing value start: " + numberValue);
        if (value.isEmpty()) {
            numberValue = 0.d;
            return;
        }
        try {
            numberValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            numberValue = 0.d;
            e.printStackTrace();
        }
//        logger.log("parsing value end: " + numberValue);
    }

    public boolean hasFactorial() {
        return hasPostOperations() && getPostOperations().stream().anyMatch( o -> o instanceof FactorialPostOperation);
    }

    public void removeLastPostOper() {
        getPostOperations().remove(getLastPostOper());
    }
}
