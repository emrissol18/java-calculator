package com.emrissol.event;

import com.emrissol.Manager;
import com.emrissol.event.operator.AbstractOperatorActionListener;
import com.emrissol.expression.Expression;
import com.emrissol.ui.UIManager;
import java.awt.event.ActionEvent;

public class DelActionListener extends AbstractOperatorActionListener {

    private StringBuilder stringBuilder = new StringBuilder();

    public DelActionListener(Manager manager, UIManager uiManager) {
        super(manager, uiManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.err.println("\n\tDEL");
        if (uiManager.getJTextField().getText().isEmpty()) {
            System.out.println("return");
            return;
        }

        Expression expression = null;
        int type = 0;
        if (manager.hasCurrent()) {
            expression = manager.getCurrentExp();
            type = 1;
        }
        else if (manager.hasCurrentParent()) {
            if (manager.getCurrentParentExp().hasChildren()) {
                // get last child of current parent
                Expression parentPrevChild = manager.getCurrentParentExp().getChildren().peek();
                // loop hierarchy to obtain parent of last child
                while (parentPrevChild.hasChildren()) {
                    parentPrevChild = parentPrevChild.getChildren().peek();
                }
                expression = parentPrevChild.getParent().getChildren().pop();
//                type = 3;
            }
            else {
                expression = manager.getCurrentParentExp();
                type = 2;
            }
        }
        else {
            System.err.println("poll");
            expression = manager.getExpressionQueue().pollLast();
            manager.setCurrentExp(expression);
//            type = 3;
        }

        System.out.println("choosen expression (" + type + "):\n" + expression);

        if ( ! expression.getValue().isEmpty()) {
            if (expression.hasOperation()) {
                System.out.println("remove operation");
                expression.setOperation(null);
            }
            else {
                stringBuilder.setLength(0);
                stringBuilder.append(expression.getValue());
                stringBuilder.setLength(stringBuilder.length()-1);
                expression.setValue(stringBuilder.toString());
                System.out.println("remove last");

                if (expression.getValue().isEmpty() && ! expression.hasPreOperation()) {
                    System.out.println("value is empty (reset)");
                    resetCurrent(type);
                }
            }
            uiManager.trimTextFieldText();
        }
        else if (expression.hasPreOperation()) {
            System.out.println("remove PreOperation");
            int preOperLength = expression.getPreOperation().getText().length();
            uiManager.trimTextFieldText(preOperLength);
            resetCurrent(type);
        }
    }

    public void resetCurrent(int type) {
        System.out.printf("resetCurrent(%d)%n", type);
        switch (type) {
            // current, obtained from global list
            case 1:
                manager.getCurrentExp().removeItselfIfFromParent();
                manager.setCurrentExp(null);
                break;
            // parent
            case 2:
                manager.getExpressionQueue().removeLast(); // remove parent
                manager.setCurrentParentExp(null);
                manager.setCurrentExp(null);
                break;
            // child of parent
//            case 3:
//                manager.getCurrentExp().removeItselfIfFromParent();
//                manager.setCurrentExp(null);
//                break;

            default: break;
        }
        /*if (type == 1) {
            manager.setCurrentExp(null);
        }
        else if (type == 2) {
            manager.getExpressionQueue().removeLast(); // remove parent
            manager.setCurrentParentExp(null);
            manager.setCurrentExp(null);
        }
        else if (type == 3) {
            manager.getCurrentExp().removeItselfIfFromParent();
            manager.setCurrentExp(null);
        }*/
    }
}
