package com.emrissol.calc.expression.operation;

public interface PreOperation {
    void apply(Number thisValue);
    String getText();
}
