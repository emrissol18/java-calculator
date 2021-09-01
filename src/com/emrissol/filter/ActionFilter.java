package com.emrissol.filter;

import com.emrissol.Manager;
import com.emrissol.expression.Expression;

public class ActionFilter {

    private Manager manager;

    public ActionFilter(Manager manager) {
        this.manager = manager;
    }


    public boolean isDigitsAllowed() {
        Expression current = manager.getCurrentOrParent();
        if (current == null || ! current.isLastPreOperClosed() ) {
            return true;
        }
        return false;
    }


}
