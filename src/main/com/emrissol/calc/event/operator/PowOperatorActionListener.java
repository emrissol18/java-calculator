package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.operation.post.PowPostOperation;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class PowOperatorActionListener extends AbstractOperatorActionListener {

    public PowOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.hasCurrent() || manager.getCurrentExp().hasPostOperations()) {
            return;
        }
        manager.getCurrentExp().getPostOperations().add(new PowPostOperation());
    }
}
