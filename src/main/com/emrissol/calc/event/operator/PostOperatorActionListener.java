package com.emrissol.calc.event.operator;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.AbstractOperatorActionListener;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.ui.UIManager;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class PostOperatorActionListener extends AbstractOperatorActionListener {

    private SimplePostOperation operation;

    public PostOperatorActionListener(Manager manager, UIManager uiManager, SimplePostOperation operation) {
        super(manager, uiManager);
        this.operation = operation;
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.hasExpressions()) {
            return;
        }
        Expression current = manager.getCurrentExp();
        // add operation to current expression if present
        // (if current present (thus has value) and if current is parent or has children)
        if (manager.hasCurrent() && ( ! current.isParent() || current.hasChildren()) ) {
            manager.getCurrentExp().setOperation(operation);
            // set current's parent (nullable)
            manager.setCurrentExp(manager.getCurrentExp().getParent());
            return;
        }

        // else change post operation of last operation
        Optional<Expression> expToChangeSign = Optional.empty();
        // last child of current parent
        if (manager.hasCurrent() && current.lastChildHasOperation()) {
            expToChangeSign = Optional.of(current.peekLastChild());
        }
        // or last expression from global queue
        else if (manager.peekLastExp().peekLastChildOrSelf().hasOperation()) {
            Expression last = manager.peekLastExp().peekLastChildOrSelf();
            expToChangeSign = Optional.of(last);
        }
        // change sign if present and return
        if (expToChangeSign.isPresent()) {
            expToChangeSign.get().setOperation(operation);
            return;
        }

    }
}
