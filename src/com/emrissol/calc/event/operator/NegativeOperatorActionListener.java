package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.Operation;
import com.emrissol.calc.log.Logger;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class NegativeOperatorActionListener extends AbstractOperatorActionListener {

    private static final Logger logger = new Logger(NegativeOperatorActionListener.class);

    public NegativeOperatorActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {

        if (manager.hasCurrent()) {
            /*Expression current = manager.getCurrentExp();
            if (current.isNegative()) {
                current.setValue(current.getValue().replaceFirst(Operation.NEGATIVE.getText(), ""));
            }*/
            Expression current = manager.getCurrentExp();
            if ( ! current.isParent()) {
                manager.getCurrentExp().toggleNegative();
            }
        }
        else {
            Expression newExpression = new Expression();
            newExpression.toggleNegative();
            manager.setAndAddCurrentExp(newExpression);
        }

        // if has preoperation then it should be with '-' prefix as negative else add prefix to expression value
        // value as '-44' would be parsed as negative
//        uiManager.refreshLayout();
//        if (manager.hasCurrentParent()) {
//            uiManager.setText(manager.getCurrentParentExp());
//        }

        String cv = null;
        if (manager.hasCurrent()) {
            cv = manager.getCurrentExp().getLayout();
        }
        logger.log("current: " + cv);
    }

}
