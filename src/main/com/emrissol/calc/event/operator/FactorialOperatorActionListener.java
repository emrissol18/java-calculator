package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.operation.post.FactorialPostOperation;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class FactorialOperatorActionListener extends AbstractOperatorActionListener {

    private static Logger logger = new Logger(FactorialOperatorActionListener.class);

    public FactorialOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if (actionFilter.isFactorialAllowed()) {
            manager.getCurrentExp().getPostOperations().add(new FactorialPostOperation());
            logger.log("factorial is allowed");
        }
    }
}
