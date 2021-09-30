package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.result.ResultFormatter;
import com.emrissol.calc.result.ResultResolver;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class EqualOperatorActionListener extends AbstractOperatorActionListener {

    public EqualOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        if ( ! manager.hasCurrent() && manager.hasExpressions()) {
            return;
        }

        // parse value for last expression (cast string to double)
        manager.getCurrentExp().parseValue();

        // calculate all expressions
        ResultResolver resultResolver = new ResultResolver();
        Double result = resultResolver.calcAll(manager.getExpressionQueue());

        // reset all
        uiManager.clearAll();
        manager.clearAll();
        Expression.resetID();

        // format result value
        ResultFormatter resultFormatter = new ResultFormatter();
        String formattedResult = resultFormatter.format(result);

        // create expression with result value, which will serve as start expreesion in new chain
        Expression resultExpression = new Expression(formattedResult);

        // set as current and add (single) expression into global deque
        manager.setAndAddCurrentExp(resultExpression);
    }

}
