package com.emrissol.calc.expression;

import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.HtmlStringUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents expression object. Expressions objects could be structured hierarchically.
 * Each expression structure can consist from one or more pre-post operations, value (digits) or simple post operation (sign).
 */
@Getter
@Setter
public class Expression {

    private static final Logger logger = new Logger(Expression.class);
    static short ID = 0;
    protected short id;

    private double numberValue;
    protected String value = "";
    protected SimplePostOperation operation;

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

    public Expression(double numberValue, SimplePostOperation operation) {
        this.value = String.valueOf(numberValue);
        this.numberValue = numberValue;
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
        getLastClosablePreOper().setOpen(isOpen);
    }

    public boolean isLastPreOperClosable() {
        return hasPreOperations() && getPreOperations().peekLast().isClosable();
    }

    public AbstractPrePostOperation getLastClosablePreOper() {
        AbstractPrePostOperation last = getPreOperations().peekLast();
        if ( ! last.isClosable()) {
            Iterator<AbstractPrePostOperation> descIterator = getPreOperations().descendingIterator();
            descIterator.next(); // skip last
            while ( descIterator.hasNext() && ! last.isClosable()) {
                last = descIterator.next();
            }
        }
        return last;
    }

    public boolean isLastPreOperClosed() {
        if ( ! hasPreOperations()) {
            return true;
        }
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
            getParent().getChildren().remove(this);
            return true;
        }
        return false;
    }

    public void addExpression(Expression expression) {
        expression.setParent(this);
        getChildren().add(expression);
    }

    /**
     * Create expression layout. If expression has children the layout of each children will be extracted recursively.<br>
     * <i>Note: </i> Usually the first call of this method in recurive chain will be performed by expressions
     * that has children (get itslef layout and its children) or has no parent (get itslef layout).
     * @return expression layout.
     */
    public String getLayout() {
        StringBuilder stringBuilder = new StringBuilder();
        if (hasChildren()) {
            getChildren().forEach( e -> stringBuilder.append(e.getLayout()) );
        }

        String thisValue = getValue();
        stringBuilder.append(thisValue);

        boolean operationAppended = false;

        String operationText = hasOperation() ? getOperation().getText() : "";

        if (hasPreOperations() || hasPostOperations()) {
            StringBuilder chunkStart = new StringBuilder();
            StringBuilder chunkEnd = new StringBuilder();

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

            chunkEnd.append(operationText);
            // concat chunks
            stringBuilder
                    .insert(0, chunkStart.toString())
                    .append(chunkEnd.toString());
        }
        else {
            stringBuilder.append(operationText);
        }
        return stringBuilder.toString();
    }

    /**
     * Find most top parent expression (most upper) in expression hierarchy,<br>
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
     * Find most last expression (most lower) in expression hierarchy if its parent does not have post operations<br>
     * OR its last preOperation is open,<br>
     * i.e.: this -> last_child -> last_child...<br/>
     * or itself.
     * @return Expression object
     */
    public Expression getDescendantChildOrItself() {
        // if it has post operation(s) then they need be removed first
        // OR
        // if it has closable pre operation(s) and they are closed, should be opened first ( e.g. cos(root(2+2)) <-- open first left parenthese )
        if (hasPostOperations() || isLastPreOperClosed()) {
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
        return id == ((Expression) object).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, operation);
    }

    @Override
    public String toString() {
        return "\n=====================================Expression#"+id+"{\n" +
                ", parentId =" + (parent == null ? "null" : parent.getId()) +
                ", value=" + value +
                ", numberValue=" + numberValue +
                ", operation=" + operation +
                ",\npreOperations =" + (getPreOperations().isEmpty() ? "empty" :
                "\n\t\t" + getPreOperations().stream().map(AbstractPrePostOperation::toString).collect(Collectors.joining("\n\t\t"))
        ) +
                ",\npostOperations =" + (getPostOperations().isEmpty() ? "empty" :
                "\n\t\t" + getPostOperations().stream().map(AbstractPrePostOperation::toString).collect(Collectors.joining("\n\t\t"))
        ) +
                ",\nexpressions =" +
                (getChildren().isEmpty() ? "empty" :
                        "\n\t\t" + getChildren().stream().map(Expression::toString).collect(Collectors.joining("\n\t\t"))
                ) +
                '}';
    }

    public boolean removeLastDigitAndIsEmpty() {
        value = HtmlStringUtil.removeLastChar(value);
        if ( ! hasValue()) {
            numberValue = 0d;
            return true;
        }
        return false;
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

    public boolean isParent() {
        return (hasClosablePreOperations() && getPreOperations().stream().anyMatch( o -> o.isClosable())) || hasChildren();
    }

    public void addToValue(String value) {
        setValue(getValue() + value);
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

    public void removeLastPostOper() {
        getPostOperations().remove(getLastPostOper());
    }

    public double resolveValue() {
        applyPrePostOperations(getPostOperations());
        applyPrePostOperations(getPreOperations());
        logger.log("resolved exp value: " + numberValue);
        return numberValue;
    }

    public void applyPrePostOperations(Collection<AbstractPrePostOperation> operations) {
        if (operations == null || operations.isEmpty()) {
            return;
        }
        for (AbstractPrePostOperation o : operations) {
            numberValue = o.apply(numberValue);
        }
    }
}
