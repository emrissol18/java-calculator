package com.emrissol.expression.operation;

public class SqrtOperation implements PreOperation {

    @Override
    public void apply(Number number) {
         number = Math.sqrt(number.doubleValue());
    }

    @Override
    public String toString() {
        return "SqrtOperation";
    }
}
