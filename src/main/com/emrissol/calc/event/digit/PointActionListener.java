package com.emrissol.calc.event.digit;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class PointActionListener extends AbstractOperatorActionListener {

    private static Logger logger = new Logger(PointActionListener.class);

    public PointActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {

        if ( ! actionFilter.isDigitsAllowed()) {
            logger.log("point not allowed");
            return;
        }

        String point = OperatorText.POINT;
        Expression expression = new Expression(point);
        if ( ! manager.hasCurrent()) {
            manager.setAndAddCurrentExp(expression);
        }
        else if (manager.getCurrentExp().isParent()) {
            manager.setAndAddCurrentExp(expression);
        }
        else if ( ! manager.getCurrentExp().hasPoint()) {
            manager.getCurrentExp().addToValue(point);
        }

    }
}
