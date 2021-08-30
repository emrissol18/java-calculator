package com.emrissol.ui;

import com.emrissol.Manager;
import com.emrissol.event.special.*;
import com.emrissol.event.digit.DigitActionListener;
import com.emrissol.event.operator.*;
import com.emrissol.expression.OperatorText;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.factory.JButtonDigitFactory;
import com.emrissol.ui.factory.JButtonOperatorFactory;
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
        JButton jButtonDel = operatorFactory.create("del");
        jButtonDel.setActionCommand(Operation.DEL.getText());
        jButtonDel.addActionListener(new DelActionListener(manager, uiManager));
        jPanel.add(jButtonDel);

        JButton jButtonParentheseRight = operatorFactory.create(OperatorText.PARENTHESES_RIGHT);
        jButtonParentheseRight.setActionCommand(jButtonParentheseRight.getText());
        jButtonParentheseRight.addActionListener(new ParentheseRightActionListener(manager, uiManager));
        jPanel.add(jButtonParentheseRight, "wrap");

        JButton jButtonClear = operatorFactory.create("C");
        jButtonClear.setActionCommand(Operation.CLEAR.getText());
        jButtonClear.addActionListener(new ClearAllActionListener(manager, uiManager));
        jPanel.add(jButtonClear);

        JButton jButtonPercent = operatorFactory.create("%");
        jButtonPercent.setActionCommand(Operation.PERCENT.getText());
        jButtonPercent.addActionListener(new PercentActionListener(manager, uiManager));
        jPanel.add(jButtonPercent);

        JButton jButtonRoot = operatorFactory.create(OperatorText.ROOT);
        jButtonRoot.setActionCommand(jButtonRoot.getText());
        jButtonRoot.addActionListener(new SqrtActionListener(manager, uiManager));
        jPanel.add(jButtonRoot);

        JButton jButtonMultiply = operatorFactory.create(Operation.MULTIPLY.getText());
        jButtonMultiply.setActionCommand(jButtonMultiply.getText());
        jButtonMultiply.addActionListener(new PostOperatorActionListener(manager, uiManager, Operation.MULTIPLY));
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

        JButton jButtonDivide = operatorFactory.create(Operation.DIVIDE.getText());
        jButtonDivide.setActionCommand(jButtonDivide.getText());
        jButtonDivide.addActionListener(new PostOperatorActionListener(manager, uiManager, Operation.DIVIDE));
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

        JButton jButtonSubstruct = operatorFactory.create(Operation.SUBSTRUCT.getText());
        jButtonSubstruct.setActionCommand(jButtonSubstruct.getText());
        jButtonSubstruct.addActionListener(new PostOperatorActionListener(manager, uiManager, Operation.SUBSTRUCT));
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

        JButton jButtonAdd = operatorFactory.create(Operation.ADD.getText());
        jButtonAdd.setActionCommand(jButtonAdd.getText());
        jButtonAdd.addActionListener(new PostOperatorActionListener(manager, uiManager, Operation.ADD));
        jPanel.add(jButtonAdd);

        JButton jButtonNegative = operatorFactory.create(Operation.NEGATIVE.getText());
        jButtonNegative.setActionCommand(jButtonNegative.getText());
        jButtonNegative.addActionListener(new NegativeActionListener(manager, uiManager));
        jPanel.add(jButtonNegative);

        JButton jButtonPoint = operatorFactory.create(Operation.POINT.getText());
        jButtonPoint.setActionCommand(jButtonPoint.getText());
        jPanel.add(jButtonPoint);

        JButton jButton0 = digitFactory.create("0");
        jButton0.setActionCommand("0");
        jPanel.add(jButton0);

//        JButton jButtonEqual = new JButtonDigitFactory(Collections.emptyList()).create(Operation.EQUALS.getText());
        digitFactory.setListeners(List.of(new EqualOperatorActionListener(manager, uiManager)));
        JButton jButtonEqual = digitFactory.create(Operation.EQUALS.getText());
        jButtonEqual.setActionCommand(jButtonEqual.getText());
//        jButtonEqual.addActionListener(new OperatorActionListener(manager, this));
//        jButtonEqual.addActionListener( actionEvent -> uiManager.getJTextField().setText(""));
        jPanel.add(jButtonEqual);
    }

}
