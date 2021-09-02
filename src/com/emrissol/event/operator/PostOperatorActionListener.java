package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class PostOperatorActionListener extends AbstractOperatorActionListener {

    private Operation operation;

    public PostOperatorActionListener(Manager manager, UIManager uiManager, Operation operation) {
        super(manager, uiManager);
        this.operation = operation;
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.hasExpressions()) {
            return;
        }

        Optional<Expression> current = manager.getCurrentOrParent();

        Optional<Expression> expToChangeSign = Optional.empty();
        // last child of current parent
        if (current.isPresent() && current.get().lastChildHasOperation()) {
            expToChangeSign = Optional.of(current.get().peekLastChild());
        }
        // or last expression from global queue
        else if (manager.peekLastExp().hasOperation()) {
            expToChangeSign = Optional.of(manager.peekLastExp());
        }
        // change sign if present and return
        if (expToChangeSign.isPresent()) {
            expToChangeSign.get().setOperation(operation);
            return;
        }

        // else just add sign to current expression
        if (manager.hasCurrent()) {
            manager.getCurrentExp().setOperation(operation);
            manager.setCurrentExp(null);
        }

    }
}
