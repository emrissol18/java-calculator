package com.emrissol.calc.result;

import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.log.Logger;
import java.util.Deque;
import java.util.Iterator;

/**
 * Class for calculation final result of all expressions hierarchy.
 */
public class ResultResolver {

    private static Logger logger = new Logger(ResultResolver.class);

    private double value;

    public ResultResolver() {
        logger.setActive(false);
    }

    /**
     * Add expValue to global value depending on operation.
     *
     * @param value global value
     * @param expValue resolved expression value
     * @param operation expression's operation
     * @return result
     */
    public strictfp double calcOperation(double value, double expValue, SimplePostOperation operation) {
        logger.log("calcOperation() [opeartion is " + operation.toString() + "]");
        switch (operation) {
            case ADD: value += expValue; break;
            case SUBSTRUCT: value -= expValue; break;
            case DIVIDE: value /= expValue; break;
            case MULTIPLY: value *= expValue; break;
        }
        logger.log("calcOperation [value]: " + value);
        return value;
    }


    /**
     * Calculate result of particular expression.<br/>
     * If an expression has children then {@link #calcAll(Deque)} will be called<br/>
     * and each children will be calculated by this method and so on.
     *
     * @param expression expression
     * @return expression's result
     */
    public strictfp double calcExpression(Expression expression) {
//        logger.log("calcExpression() [layout]: " + expression.getLayout() + "\t[numberValue]: " + expression.getNumberValue());
        if (expression.isParent()) {
//            logger.log("calcExpression [isParent]");
            expression.setNumberValue(calcAll(expression.getChildren()));
//            logger.log("parent numberValue after set calcAllResult: " + expression.getNumberValue());
        }
        double v = expression.resolveValue();
        logger.log("calcExpression [resolved value]: " + v);
        return v;
    }


    public double calcAll(Deque<Expression> expressions) {
        double result = calcAllInner(expressions);
        value = 0d;
        return result;
    }
    /**
     * Calculate total result of all expressions that dwell in deque applying {@link #calcExpression(Expression)} method for each.
     *
     * @param expressions expressions
     * @return final result
     */
    private strictfp double calcAllInner(Deque<Expression> expressions) {
        logger.log("calcAll");
        if (expressions == null || expressions.isEmpty()) {
            logger.log("return");
            return 0d;
        }
        // calc 1st (and only one) expression
        if (expressions.size() == 1) {
            logger.log("calc 1st expression");
            return calcExpression(expressions.peek());
        }

        // variable for final result of all expressions
        double value = 0d;

        Iterator<Expression> iterator = expressions.iterator();

        // extract 1st expression's data: value & operation
        Expression exp1 = iterator.next();
        value = calcExpression(exp1);
        SimplePostOperation lastOperation1 = exp1.getOperation();

        while (iterator.hasNext()) {
            exp1 = iterator.next();

            System.out.println();
            logger.log("iterate");
            // end of expression (sub) set, last expression has no operation
            if (lastOperation1 == null) {
                logger.log("break");
                break;
            }
            // calc expressions that has operations with higher priority
            else if (exp1.hasOperation() && isOperationMultOrDivide(exp1.getOperation())) {
//                double accMult = calcPriorOperationsChain(iterator, exp1, lastOperation1);
//                value = calcOperation(value, accMult, lastOperation1);
                Expression subExp = calcPriorOperationsChain(value, iterator, exp1, lastOperation1);
                value = subExp.getNumberValue();
                lastOperation1 = subExp.getOperation();
            }
            else {
                logger.log("simple exp block");
                double expValue = calcExpression(exp1);
                value = calcOperation(value, expValue, lastOperation1);
                lastOperation1 = exp1.getOperation();
            }
        }
        System.out.println("[calcAll] - RESULT IS: " + value);
        return value;
    }


    /**
     * Calculate chain of expressions that followed by MULTIPLE or DIVIDE operation, which have higher priority.<br/>
     *
     * e.g.: if we have such expression "1 + 2 + 3 / 4 * 2"<br/>
     * then sub expression of "3 / 4 * 2" must be calclulated first<br/>
     * and then added to prev expression - "1 + 2 + (3 / 4 * 2)".
     *
     * @param value global value
     * @param iterator iterator
     * @param exp1 current expression (last iterator.next())
     * @return expression that holds result value in <code>numberValue</code> and last operation in <code>opeartion</code>
     */
    private strictfp Expression calcPriorOperationsChain(double value, Iterator<Expression> iterator, Expression exp1, SimplePostOperation lastOperation1) {
        System.out.println();
        logger.log("mult block");

        Expression exp1_1 = exp1;

        // calc current expression for a start
        double accMult = calcExpression(exp1_1);

        SimplePostOperation lastOperation1_1 = exp1_1.getOperation();

        while (iterator.hasNext() && isOperationMultOrDivide(lastOperation1_1)) {
            exp1_1 = iterator.next();

            double expValue = calcExpression(exp1_1);
            accMult = calcOperation(accMult, expValue, lastOperation1_1);

            // remember next's exp operation for comparisons
            lastOperation1_1 = exp1_1.getOperation();

            logger.log("[exp1_1]: " + exp1_1.getLayout());
        }
        logger.log("result of mult chain is: " + accMult);
        // add accMult (result value of mult\divide expression chain) to global value
        // and apply operation remembered before mult\divide expression chain calculation started
//        return calcOperation(value, accMult, lastOperation1);
//        return accMult;
        Expression multDivideSunExp = new Expression();
        multDivideSunExp.setOperation(lastOperation1_1);
        double subExpResult = calcOperation(value, accMult, lastOperation1);
        multDivideSunExp.setNumberValue(subExpResult);
        return multDivideSunExp;
    }

    public boolean isOperationMultOrDivide(SimplePostOperation operation) {
        return operation.equals(SimplePostOperation.MULTIPLY) || operation.equals(SimplePostOperation.DIVIDE);
    }

}
