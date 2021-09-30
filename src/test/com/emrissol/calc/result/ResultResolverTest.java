package com.emrissol.calc.result;

import com.emrissol.calc.TestItemsFactory;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.expression.operation.post.PowPostOperation;
import com.emrissol.calc.expression.operation.pre.NegativePreOperation;
import com.emrissol.calc.expression.operation.pre.SqrtPreOperation;
import org.junit.Assert;
import org.junit.Test;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ResultResolverTest {

    public static TestItemsFactory factory = new TestItemsFactory();

    private static ResultResolver resultResolver = new ResultResolver();

    public static final double delta = 0.0001;
    public static void assertEquals(double exprected, double actual) {
        Assert.assertEquals(exprected, actual, delta);
    }

    @Test
    public void testExpressionsSet1() {
        // 1 + 2 x 4 x 2
        Deque<Expression> deque = new LinkedList<>(List.of(
                factory.createExp(1, SimplePostOperation.MULTIPLY),
                factory.createExp(2, SimplePostOperation.ADD),
                factory.createExp(3, SimplePostOperation.MULTIPLY),
                factory.createExp(5)
        ));
//        parseValueForAll(deque);
        double resultValue = resultResolver.calcAll(deque);
        assertEquals(17.0d, resultValue);
    }


    // -1 * root( 2 + 4 * 2 ) + 4 * 2
    @Test
    public void testExpressionsSet2() {
        // -1*
        Expression minusOne = new Expression("1", SimplePostOperation.MULTIPLY);
        minusOne.getPreOperations().add(new NegativePreOperation());

        // root(2+4*2)+
        Expression root = new Expression("", SimplePostOperation.ADD);
        root.getPreOperations().add(new SqrtPreOperation());
        root.addExpression(factory.createExp(2, SimplePostOperation.ADD));
        root.addExpression(factory.createExp(4, SimplePostOperation.MULTIPLY));
        root.addExpression(factory.createExp(2));

        // 4*2
        Expression four = factory.createExp(4, SimplePostOperation.MULTIPLY);
        Expression two = factory.createExp(2);

        Deque<Expression> deque = new LinkedList<>(List.of(
                minusOne,
                root,
                four,
                two
        ));
//        parseValueForAll(deque);
        double resultValue = resultResolver.calcAll(deque);
        System.out.println("result is: " + resultValue);
        assertEquals(4.83772d, resultValue);
    }


    //2^32
    @Test
    public void testExpressionsSet3(){
        Expression e100 = factory.createExp(2);
        PowPostOperation powO = new PowPostOperation();
        powO.setValue("32");
        e100.getPostOperations().add(powO);
        Double result = resultResolver.calcAll(new LinkedList<>(List.of(e100)));
        assertEquals(4294967296d, result);
    }

    // root( 10^3 + 9 )^6 + 4 * 2 - 4
    @Test
    public void testExpressionsSet4() {
        // root(10^3 + 9)^6 +
        Expression root = factory.createExp(0, SimplePostOperation.ADD);
        root.getPreOperations().add(new SqrtPreOperation());

        // 10^3+
        Expression tenPowThree = factory.createExp(10, SimplePostOperation.ADD);
        tenPowThree.getPostOperations().add(factory.createPow("3"));
        root.addExpression(tenPowThree);

        // 9
        Expression nine = factory.createExp(9);
        root.addExpression(nine);

        // ^6
        root.getPostOperations().add(factory.createPow("6"));

        // 4*
        Expression four = factory.createExp(4, SimplePostOperation.MULTIPLY);
        // 2
        Expression two = factory.createExp(2, SimplePostOperation.SUBSTRUCT);

        Expression four2 = factory.createExp(4);

        Deque<Expression> deque = new LinkedList<>(List.of(
                root,
                four,
                two,
                four2
        ));
        double resultValue = resultResolver.calcAll(deque);
        System.out.println("result is: " + resultValue);
        Assert.assertEquals(1027243733, (long) resultValue);
    }
}
