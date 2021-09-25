package com.emrissol.calc.result;

import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.expression.operation.post.PowPostOperation;
import com.emrissol.calc.expression.operation.pre.SqrtPreOperation;
import org.junit.Test;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ResultResolverTest {

    private ResultResolver resultResolver = new ResultResolver();

    @Test
    public void testExpressionsSet1() {
        // 1 + 2 x 4 x 2
        Deque<Expression> deque = new LinkedList<>(List.of(
                new Expression(1, SimplePostOperation.MULTIPLY),
                new Expression(2, SimplePostOperation.ADD),
                new Expression(3, SimplePostOperation.MULTIPLY),
                new Expression(5, null)
        ));
        ResultResolver result = new ResultResolver();
        double resultValue = result.calcAll(deque);
        System.out.println("result is: " + resultValue);
//        Assert.assertEquals(17.0d, resultValue, 0.0001);
//        Assert.assertEquals(17.0d, resultValue, 0.0001);
    }


    @Test
    public void testPow(){
        Expression e100 = new Expression(2, null);
        PowPostOperation powO = new PowPostOperation();
        powO.setValue("32");
        e100.getPostOperations().add(powO);
        Double result = resultResolver.calcAll(new LinkedList<>(List.of(e100)));
        if (result > Double.MAX_VALUE) {
            System.out.println("result is greater then maxDouble value");
        }
        System.out.println("double: " + result);
        System.out.println("long: " + (result.longValue()));
    }
    /*public void testExpressionsSet4() {
        Expression e1 = new Expression(1, SimplePostOperation.ADD);
        Expression e2 = new Expression(2, SimplePostOperation.MULTIPLY);
        Expression e3 = new Expression(3, SimplePostOperation.SUBSTRUCT);
        Expression e4 = new Expression(4, SimplePostOperation.MULTIPLY);

        Expression e5 = new Expression(0, SimplePostOperation.ADD);
        e5.getPreOperations().add(new SqrtPreOperation());
        e5.addExpression(new Expression(8, SimplePostOperation.ADD));
        e5.addExpression(new Expression(5, SimplePostOperation.MULTIPLY));
        e5.addExpression(new Expression(4, SimplePostOperation.ADD));
        e5.addExpression(new Expression(6, null));


        Expression e6 = new Expression(7, SimplePostOperation.SUBSTRUCT);
        Expression e7 = new Expression(8, SimplePostOperation.MULTIPLY);
        Expression e8 = new Expression(9, SimplePostOperation.MULTIPLY);
        Expression e9 = new Expression(10, SimplePostOperation.MULTIPLY);
        Expression e10 = new Expression(1, SimplePostOperation.ADD);


        Expression e11 = new Expression(0, SimplePostOperation.MULTIPLY);
        e11.getPreOperations().add(new SqrtPreOperation());
        e11.addExpression(new Expression(1, SimplePostOperation.ADD));
        e11.addExpression(new Expression(2, SimplePostOperation.MULTIPLY));

        Expression e12 = new Expression(0, SimplePostOperation.MULTIPLY);
        e12.getPreOperations().add(new SqrtPreOperation());
        e12.addExpression(new Expression(3, SimplePostOperation.ADD));
        e12.addExpression(new Expression(5, null));

        e11.addExpression(e12);

        e11.addExpression(new Expression(6, null));

        Expression e13 = new Expression(7, SimplePostOperation.MULTIPLY);
        Expression e14 = new Expression(10, null);
    }*/


    public Deque<Expression> getExpressionsSet1() {
        Expression e5 = new Expression("", SimplePostOperation.ADD);
        e5.getPreOperations().add(new SqrtPreOperation());
        e5.addExpression(new Expression(8, SimplePostOperation.ADD));
        e5.addExpression(new Expression(5, SimplePostOperation.MULTIPLY));
        e5.addExpression(new Expression(4, SimplePostOperation.ADD));
        e5.addExpression(new Expression(6, null));
        return new LinkedList(List.of(
                new Expression(1, SimplePostOperation.ADD),
                new Expression(2, SimplePostOperation.MULTIPLY),
                new Expression(3, SimplePostOperation.SUBSTRUCT),
                new Expression(4, SimplePostOperation.MULTIPLY),
                e5

        ));

    }
}
