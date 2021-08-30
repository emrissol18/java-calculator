package com.emrissol.event.operator;

import com.emrissol.Manager;
import com.emrissol.event.AbstractOperatorActionListener;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class PercentActionListener extends AbstractOperatorActionListener {
    public PercentActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformedHook(ActionEvent actionEvent) {
        if ( ! manager.getExpressionQueue().isEmpty()) {
            manager.getExpressionQueue().forEach(System.out::println);
        }
//        if (manager.hasCurrentParent() && manager.getCurrentParentExp().hasChildren()) {
//            System.out.println("parent");
//            manager.getCurrentParentExp().getChildren().forEach(System.out::println);
//        }

    }
}
