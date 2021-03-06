package com.emrissol.calc;

import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.filter.ActionFilter;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ActionFilterTest {

    public static Manager manager = new Manager();
    public static ActionFilter actionFilter = new ActionFilter(manager);

    @Test
    public void testAllowDigit() {
        Expression expression1 = new Expression();
        expression1.addToValue("10");
        expression1.setOperation(SimplePostOperation.ADD);
        manager.setAndAddCurrentExp(expression1);
        assertTrue(actionFilter.isDigitsAllowed());
    }
}
