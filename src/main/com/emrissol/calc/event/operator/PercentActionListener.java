package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class PercentActionListener extends AbstractOperatorActionListener {
    public PercentActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        if ( manager.hasExpressions()) {
            manager.getExpressionQueue().forEach(exp -> {
                System.out.println("global- " + exp);
            });
        }
    }
}
