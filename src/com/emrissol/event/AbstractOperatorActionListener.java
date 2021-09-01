package com.emrissol.event;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;
import com.emrissol.filter.ActionFilter;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public abstract class AbstractOperatorActionListener implements ActionListener {

    protected Manager manager;
    protected UIManager uiManager;
    protected static ActionFilter actionFilter;

    public AbstractOperatorActionListener(Manager manager, UIManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
        actionFilter = new ActionFilter(manager);
    }

    protected abstract void actionPerformedHook(ActionEvent actionEvent);

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionPerformedHook(actionEvent);
        refreshLayout();
    }

    private void refreshLayout() {
        if (manager.getExpressionQueue().isEmpty()) {
            uiManager.clearAll();
            return;
        }
        Optional<Expression> expression = Optional.empty();
        if (manager.hasCurrentParent()) {
//            System.out.println("optional parent");
            expression = Optional.ofNullable(manager.getCurrentParentExp().getAncestorParent());
        }
        else if (manager.hasCurrent()) {
//            System.out.println("optional current");
            expression = Optional.ofNullable(manager.getCurrentExp());
        }
        else {
//            System.out.println("optional peekLast");
            expression = Optional.ofNullable(manager.peekLastExp());
        }

        expression.ifPresentOrElse(exp -> uiManager.refreshLayout(exp), () -> System.out.println("expression is not present"));

    }
}
