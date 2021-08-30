package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.dev.log.Logger;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class NegativeActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(NegativeActionListener.class);

    public NegativeActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        // if has preoperation then it should be with '-' prefix as negative else add prefix to expression value
        // value as '-44' would be parsed as negative
//        uiManager.refreshLayout();
//        if (manager.hasCurrentParent()) {
//            uiManager.setText(manager.getCurrentParentExp());
//        }

        String cv = null;
        String pv = null;
        if (manager.hasCurrent()) {
            cv = manager.getCurrentExp().getLayout();
        }
        else if (manager.hasCurrentParent()) {
            pv = manager.getCurrentParentExp().getLayout();
        }
        logger.log("current: " + cv);
        logger.log("parent: " + pv);
//        logger.log("manager.getCurrentExp() = " + (manager.hasCurrent() ?  manager.getCurrentExp().getLayout() : null));
//        logger.log("manager.getCurrentParentExp() = " + (manager.hasCurrentParent() ? manager.getCurrentParentExp().getLayout() : null));
    }

}
