package com.emrissol.calc.event;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.operator.PostOperatorActionListener;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.ui.UIManager;

/**
 * Factory for simple post operation (+,-,/,*) action listeners.
 */
public class PostOperationActionListenerFactory {

    private Manager manager;
    private UIManager uiManager;

    public PostOperationActionListenerFactory(Manager manager, UIManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
    }

    public AbstractOperatorActionListener create(SimplePostOperation operation) {
        return new PostOperatorActionListener(manager, uiManager, operation);
    }

}