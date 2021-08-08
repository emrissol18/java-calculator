package com.emrissol.expression;

import com.emrissol.expression.operation.Operation;
import com.emrissol.expression.operation.PreOperation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter
@Setter
public class Expression {

    static short ID = 0;
    protected short id;
    protected String value = "";
    protected PreOperation preOperation;
    protected Operation operation;

    protected Expression parent;

    @Getter(AccessLevel.NONE)
    protected Stack<Expression> children;

    public Expression() {
        this.id = ID++;
    }

    public Expression(PreOperation preOperation) {
        this();
        this.preOperation = preOperation;
    }

    public void applyPreOperation(Number number) {
        if (preOperation != null) {
            preOperation.apply(number);
        }
    }
    public Expression(String value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public Number calc(Number number2) {
        if (preOperation != null) {

        }
        return null;
    }

    public boolean hasOperation() {
        return operation != null;
    }

    public boolean hasPreOperation() {
        return preOperation != null;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Stack<Expression> getChildren() {
        if (this.children == null) {
            this.children = new Stack<>();
        }
        return children;
    }

    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    public void removeItselfIfFromParent() {
        if (hasParent()) {
            parent.getChildren().remove(this);
        }
    }
    public void addExpression(Expression expression) {
        expression.setParent(this);
        getChildren().add(expression);
//        System.err.println("ADDING CHILDREN " + expression.getId() + " TO PARENT " + getId());
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
        return "Expression{" +
                "id=" + id +
                ", value=" + value +
                ", operation=" + operation +
                ", preOperation =" + preOperation +
//                "\n\t\texpressions =" + stringBuilder.toString() +
                ", expressions =" +
                (getChildren().isEmpty() ? "empty" :
                        "\n\t\t" + getChildren().stream().map(Expression::toString).collect(Collectors.joining("\n\t\t"))
                )
                +
                '}';
    }
}
