package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class ParentheseRightActionListener extends AbstractOperatorActionListener {

    public ParentheseRightActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    protected void actionPerformedHook(ActionEvent actionEvent) {

        Optional<Expression> currentOptional = manager.getCurrentOrParent();

        if ( ! currentOptional.isPresent()) {
            return;
        }
        Expression current = currentOptional.get();

        if (current.isParent() ) {
            // close current's last preoperation if open
            if (actionFilter.isCloseAllowed(current)) {
                current.closeLastPreOper();
                manager.setCurrentParentExp(current.getParent());
            }
            // close parent's last pre operation
            else if (current.isLastPreOperClosed() && current.hasParent() && ! current.getParent().isLastPreOperClosed()) {
                Expression parent = current.getParent();
                parent.closeLastPreOper();
                manager.setCurrentExp(parent);
                manager.setCurrentParentExp(parent.getParent());
            }
        }
        // close parent's last pre operation of simple expression, i.e. expression that does not have pre operations
        else if (current.hasParent() && actionFilter.isCloseAllowed(current.getParent())){
            Expression parent = current.getParent();
            parent.closeLastPreOper();
            manager.setCurrentExp(parent);
            manager.setCurrentParentExp(parent.getParent());
        }

    }
}