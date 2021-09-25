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

        // else add operation to current expression
        // (if current present (thus it has value) or if current is parent and has children > 0)
        if (manager.hasCurrent() && ( ! current.isParent() || current.hasChildren()) ) {
            manager.getCurrentExp().setOperation(operation);
            // set parent if has or null
            manager.setCurrentExp(manager.getCurrentExp().getParent());
        }

    }
}
