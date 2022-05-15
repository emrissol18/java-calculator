package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.result.ResultFormatter;
import com.emrissol.calc.result.ResultResolver;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class EqualOperatorActionListener extends AbstractOperatorActionListener {

    private ResultResolver resultResolver = new ResultResolver();
    private ResultFormatter resultFormatter = new ResultFormatter();

    public EqualOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        if ( ! manager.hasCurrent() ) {
            return;
        } else if (manager.getExpressionQueue().size() == 1) {
            Expression last = manager.getExpressionQueue().getLast();
            if ( ! last.hasPreOperations() && ! last.hasPostOperations()) {
                return;
            }
        }

        // parse value for last expression (cast string to double)
        manager.getCurrentExp().parseValue();

        // calculate all expressions
        Double result = resultResolver.calcAll(manager.getExpressionQueue());
        // format result value
        String formattedResult = resultFormatter.format(result);

        // add to history prev expressions with result
        String historyValue = uiManager.expressionsLayoutsAsString()
                .concat("=").concat(formattedResult).concat("<br/>");

        uiManager.addToHistory(historyValue);

        // reset all
        uiManager.reset();

        // create expression with result value, which will serve as start expreesion in new chain
        Expression resultExpression = new Expression(formattedResult);
        // set as current and add (single) expression into global deque
        manager.setAndAddCurrentExp(resultExpression);
    }

}
