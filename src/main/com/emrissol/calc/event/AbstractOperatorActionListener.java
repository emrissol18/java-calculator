package com.emrissol.calc.event;

import com.emrissol.calc.Manager;
import com.emrissol.calc.filter.ActionFilter;
import com.emrissol.calc.ui.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Abstract action listener for all button action listeners.
 * Its children must implement actionPerformedHook() method to be performed before layout refresh.
 */
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
            uiManager.reset();
            return;
        }
        if (manager.hasCurrent()) {
            uiManager.refreshLayout(manager.getCurrentExp().getAncestorParentOrSelf());
        } else {
            uiManager.refreshLayout();
        }
        uiManager.scrollToEnd();
    }

}
