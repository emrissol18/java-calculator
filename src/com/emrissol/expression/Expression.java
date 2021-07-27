package com.emrissol.expression;

import com.emrissol.expression.operation.Operation;
import com.emrissol.expression.operation.PreOperation;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class Expression {

    static short ID = 0;
    protected short id;
    protected String value;
    protected PreOperation preOperation;
    protected Operation operation;


    public Expression() {
        this.id = ID++;
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

//    public void appendValue(String value) {
//        this.value += value;
//    }

//    public boolean hasValue(){
//        return ! value.isEmpty();
//    }

    public boolean hasPreOperation() {
        return preOperation != null;
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
        return "Expression{" +
                "id=" + id +
                ", value=" + value +
                ", operation=" + operation +
                '}';
    }
}
