package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class NegativeActionListener extends AbstractOperatorActionListener {

    public NegativeActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if has preoperation then it should be with '-' prefix as negative else add prefix to expression value
        // value as '-44' would be parsed as negative
    }

}
