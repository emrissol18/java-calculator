package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.AbstractPrePostOperation;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;

public class ParentheseRightActionListener extends AbstractOperatorActionListener {

    public ParentheseRightActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {

//        Optional<Expression> currentOptional = manager.getCurrentOrParent();
//        Optional<Expression> currentOptional = Optional.ofNullable(manager.getCurrentExp());
        Expression current = manager.getCurrentExp();

        if (current == null) {
            return;
        }

        if (current.isParent() ) {
            // close current's last preoperation if open
            if (actionFilter.isCloseAllowed(current)) {
                current.setLastPreOperOpen(false);
//                manager.setCurrentParentExp(current.getParent());
            }
            // close parent's last pre operation
            else if (current.isLastPreOperClosed() && current.hasParent() && ! current.getParent().isLastPreOperClosed()) {
                Expression parent = current.getParent();
                parent.setLastPreOperOpen(false);
                manager.setCurrentExp(parent);
//                manager.setCurrentParentExp(parent.getParent());
            }
        }
        // close parent's last pre operation of simple expression, i.e. expression that does not have pre operations
        else if (current.hasParent() && actionFilter.isCloseAllowed(current.getParent())){
            Expression parent = current.getParent();
            parent.setLastPreOperOpen(false);
            manager.setCurrentExp(parent);
//            manager.setCurrentParentExp(parent.getParent());
        }

    }
}