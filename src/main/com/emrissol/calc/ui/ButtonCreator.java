package com.emrissol.calc.ui;

import com.emrissol.calc.event.PostOperationActionListenerFactory;
import com.emrissol.calc.event.digit.PointActionListener;
import com.emrissol.calc.event.operator.*;
import com.emrissol.calc.Manager;
import com.emrissol.calc.event.special.ClearAllActionListener;
import com.emrissol.calc.event.special.DelActionListener;
import com.emrissol.calc.event.digit.DigitActionListener;
import com.emrissol.calc.expression.OperatorText;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.ui.factory.JButtonDigitFactory;
import com.emrissol.calc.ui.factory.JButtonOperatorFactory;
import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class ButtonCreator {

    private Manager manager;
    private UIManager uiManager;
    private JButtonDigitFactory digitFactory;
    private JButtonOperatorFactory operatorFactory;

    public ButtonCreator(Manager manager, UIManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
        digitFactory = new JButtonDigitFactory(List.of(new DigitActionListener(manager, uiManager)));
        operatorFactory = new JButtonOperatorFactory(Collections.emptyList());
    }

    public void createDigitButtons(JPanel jPanel) {
        JButton jButtonPow = operatorFactory.create(OperatorText.POW);
        jButtonPow.setActionCommand(jButtonPow.getText());
        jButtonPow.addActionListener(new PowOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonPow, "wrap");

        JButton jButtonDel = operatorFactory.create("del");
        jButtonDel.setActionCommand(OperatorText.DEL);
        jButtonDel.addActionListener(new DelActionListener(manager, uiManager));
        jPanel.add(jButtonDel);

        JButton jButtonParentheseRight = operatorFactory.create(OperatorText.PARENTHESES_RIGHT);
        jButtonParentheseRight.setActionCommand(jButtonParentheseRight.getText());
        jButtonParentheseRight.addActionListener(new ParentheseRightActionListener(manager, uiManager));
        jPanel.add(jButtonParentheseRight);

        JButton jButtonFactorial = operatorFactory.create(OperatorText.FACTORIAL);
        jButtonFactorial.setActionCommand(jButtonFactorial.getText());
        jButtonFactorial.addActionListener(new FactorialOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonFactorial);

        JButton jButtonCurrentStatus = operatorFactory.create("S");
        jButtonCurrentStatus.setActionCommand(jButtonParentheseRight.getText());
        jButtonCurrentStatus.addActionListener( (e) -> {
            String cv = null;
            if (manager.hasCurrent()) {
                cv = manager.getCurrentExp().getLayout();
            }
            System.out.println("current: " + cv);
//            logger.log("current: " + cv);
        });
        jPanel.add(jButtonCurrentStatus, "wrap");

        JButton jButtonClear = operatorFactory.create("C");
        jButtonClear.setActionCommand(OperatorText.CLEAR);
        jButtonClear.addActionListener(new ClearAllActionListener(manager, uiManager));
        jPanel.add(jButtonClear);

        JButton jButtonPercent = operatorFactory.create("%");
        jButtonPercent.setActionCommand(OperatorText.PERCENT);
        jButtonPercent.addActionListener(new PercentActionListener(manager, uiManager));
        jPanel.add(jButtonPercent);

        JButton jButtonRoot = operatorFactory.create(OperatorText.ROOT);
        jButtonRoot.setActionCommand(jButtonRoot.getText());
        jButtonRoot.addActionListener(new SqrtActionListener(manager, uiManager));
        jPanel.add(jButtonRoot);

        PostOperationActionListenerFactory postOperationFactory = new PostOperationActionListenerFactory(manager, uiManager);

        JButton jButtonMultiply = operatorFactory.create(SimplePostOperation.MULTIPLY.getText());
        jButtonMultiply.setActionCommand(jButtonMultiply.getText());
        jButtonMultiply.addActionListener(postOperationFactory.create(SimplePostOperation.MULTIPLY));
        jPanel.add(jButtonMultiply);

        JButton jButton7 = digitFactory.create("7");
        jButton7.setActionCommand("7");
        jPanel.add(jButton7);
        JButton jButton8 = digitFactory.create("8");
        jButton8.setActionCommand("8");
        jPanel.add(jButton8);
        JButton jButton9 = digitFactory.create("9");
        jButton9.setActionCommand("9");
        jPanel.add(jButton9);

        JButton jButtonDivide = operatorFactory.create(SimplePostOperation.DIVIDE.getText());
        jButtonDivide.setActionCommand(jButtonDivide.getText());
        jButtonDivide.addActionListener(postOperationFactory.create(SimplePostOperation.DIVIDE));
        jPanel.add(jButtonDivide);


        JButton jButton4 = digitFactory.create("4");
        jButton4.setActionCommand("4");
        jPanel.add(jButton4);
        JButton jButton5 = digitFactory.create("5");
        jButton5.setActionCommand("5");
        jPanel.add(jButton5);
        JButton jButton6 = digitFactory.create("6");
        jButton6.setActionCommand("6");
        jPanel.add(jButton6);

        JButton jButtonSubstruct = operatorFactory.create(SimplePostOperation.SUBSTRUCT.getText());
        jButtonSubstruct.setActionCommand(jButtonSubstruct.getText());
        jButtonSubstruct.addActionListener(postOperationFactory.create(SimplePostOperation.SUBSTRUCT));
        jPanel.add(jButtonSubstruct);

        JButton jButton1 = digitFactory.create("1");
        jButton1.setActionCommand("1");
        jPanel.add(jButton1);
        JButton jButton2 = digitFactory.create("2");
        jButton2.setActionCommand("2");
        jPanel.add(jButton2);
        JButton jButton3 = digitFactory.create("3");
        jButton3.setActionCommand("3");
        jPanel.add(jButton3);

        JButton jButtonAdd = operatorFactory.create(SimplePostOperation.ADD.getText());
        jButtonAdd.setActionCommand(jButtonAdd.getText());
        jButtonAdd.addActionListener(postOperationFactory.create(SimplePostOperation.ADD));
        jPanel.add(jButtonAdd);

        JButton jButtonNegative = operatorFactory.create(OperatorText.NEGATIVE);
        jButtonNegative.setActionCommand(jButtonNegative.getText());
        jButtonNegative.addActionListener(new NegativeOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonNegative);

        JButton jButtonPoint = operatorFactory.create(SimplePostOperation.POINT.getText());
        jButtonPoint.setActionCommand(jButtonPoint.getText());
//        jButtonPoint.addActionListener(new DigitActionListener(manager, uiManager));
        jButtonPoint.addActionListener(new PointActionListener(manager, uiManager));
        jPanel.add(jButtonPoint);

        JButton jButton0 = digitFactory.create("0");
        jButton0.setActionCommand("0");
        jPanel.add(jButton0);

//        JButton jButtonEqual = new JButtonDigitFactory(Collections.emptyList()).create(Operation.EQUALS.getText());
        digitFactory.setListeners(List.of(new EqualOperatorActionListener(manager, uiManager)));
        JButton jButtonEqual = digitFactory.create(SimplePostOperation.EQUALS.getText());
        jButtonEqual.setActionCommand(jButtonEqual.getText());
//        jButtonEqual.addActionListener(new OperatorActionListener(manager, this));
//        jButtonEqual.addActionListener( actionEvent -> uiManager.getJTextField().setText(""));
        jPanel.add(jButtonEqual);
    }

}