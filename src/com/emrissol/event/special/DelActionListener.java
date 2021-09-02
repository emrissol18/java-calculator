package com.emrissol.event.special;

import com.emrissol.Manager;
import com.emrissol.dev.log.Logger;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class DelActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(DelActionListener.class);

    private StringBuilder stringBuilder = new StringBuilder();

    public DelActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.hasExpressions()) {
            logger.log("return");
            return;
        }


    }

}
