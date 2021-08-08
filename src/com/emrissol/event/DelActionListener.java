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
        if (uiManager.getJTextField().getText().isEmpty() && manager.getExpressionQueue().isEmpty()) {
            return;
        }

        if ( ! manager.hasCurrent()) {
            Expression expression = null;

            if (manager.hasCurrentParent()) {

                if (manager.getCurrentParentExp().hasChildren()) {
                    expression = manager.getCurrentParentExp().getChildren().pop();
//                    System.out.println("removed with id: " + expression.getId());
                }
                else {
                    expression = manager.getCurrentParentExp();
                    manager.setCurrentParentExp(expression.hasParent() ? expression.getParent() : null);
                    // if this parent does not have its parent it means it dwells in global expression list
                    if ( ! expression.hasParent()) {
                        manager.getExpressionQueue().remove(expression);
                    }
                }

            }
            else {
                expression = manager.getExpressionQueue().pollLast();
            }
            manager.setCurrentExp(expression);
        }
        else if (manager.getCurrentExp().hasParent()) {
            manager.getCurrentExp().removeItselfIfFromParent();
        }

        Expression current = manager.getCurrentExp();
        System.out.println(current);
        if ( current.hasValue()) {
            if (current.hasOperation()) {
                System.out.println("\t\tremove operation");
                current.setOperation(null);
            }
            else {
                System.out.println("\t\tremove last digit");
                stringBuilder.setLength(0);
                stringBuilder.append(current.getValue());
                stringBuilder.setLength(stringBuilder.length()-1);
                current.setValue(stringBuilder.toString());

                if (current.getValue().isEmpty() && ! current.hasPreOperation()) {
                    System.out.println("\t\tno digits");
                    manager.setCurrentExp(null);
//                    resetCurrent(current);
                }
            }
            uiManager.trimTextFieldText();
        }
        else if (current.hasPreOperation()) {
            System.out.println("\t\tremove PreOperation");
//            System.out.println("value: " + current.getValue());
            int preOperLength = current.getPreOperation().getText().length();
            uiManager.trimTextFieldText(preOperLength);
            manager.setCurrentExp(null);
        }
    }
}
