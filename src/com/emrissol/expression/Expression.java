package com.emrissol.expression;

import com.emrissol.Manager;
import com.emrissol.dev.log.Logger;
import com.emrissol.expression.operation.AbstractPrePostOperation;
import com.emrissol.expression.operation.Operation;
import lombok.AccessLevel;
import lombok.Getter;
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

    public Expression(String value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

//    public Number calc(Number number2) {
//        if (preOperation != null) {
//
//        }
//        return null;
//    }

    public static void resetID() {
        ID = 0;
    }
    public boolean hasValue() {
        return ! getValue().isEmpty();
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

    public void setLastPreOperOpen(boolean isClosed) {
        getPreOperations().peekLast().setOpen(isClosed);
    }
    public AbstractPrePostOperation getLastPreOper() {
        return getPreOperations().peekLast();
    }

    public boolean isLastPreOperClosed() {
        return hasPreOperations() && ! isLastPreOperOpen();
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
//        System.out.println("expression#"+ id+" length: " + length);
//        System.out.printf("expression#%d length: %d (%s)\n", id, length, stringBuilder.toString());
//        logger.logError(id + " stringBuilder to RETURN: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private String getOperationText() {
        if (hasOperation())
            return getOperation().getText();
        return "";
    }

    // find most top parent in expression hierarchy tree
    public Expression getAncestorParent() {
        if (hasParent()) {
            Expression parent = getParent();
            while (parent.hasParent()) {
                parent = parent.getParent();
            }
            return parent;
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

    public boolean isCloseAllowed() {
        // allowed IF there is open preOperation
        if (hasPreOperations() && isLastPreOperOpen()) {
            Expression lastChild = getChildren().peekLast();
            if (hasChildren() ) {
                // allow only IF value in preOperation is not empty and expression has no operation
                // ( i.e. NOT root() - here in root function no value present || NOT root(1+) - operation as last char)
                if ( ! lastChild.hasValue() || ! lastChild.hasOperation()) {
                    return true;
                }
            }
            return false;
        }
        // not allowed by default
        return false;
    }


    public void removeLastDigit() {
        if (hasValue()) {
            setValue( value.substring(0, value.length() - 1) );
        }
        else {
            logger.logError("removeLastDigit(): value.length() is <= 0");
        }
    }

    public Expression peekLastChild() {
        return getChildren().peekLast();
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
        return hasPreOperations() || hasChildren();
    }
}
