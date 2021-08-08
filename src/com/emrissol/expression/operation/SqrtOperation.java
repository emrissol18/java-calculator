package com.emrissol.expression.operation;

public class SqrtOperation implements PreOperation {

    @Override
    public void apply(Number number) {
         number = Math.sqrt(number.doubleValue());
    }

    @Override
    public String getText() {
        return Operation.SQRT.getText();
    }

    @Override
    public String toString() {
        return "SqrtOperation";
    }
}
