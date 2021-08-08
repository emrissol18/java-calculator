package com.emrissol.ui;

import com.emrissol.Manager;
import com.emrissol.event.DelActionListener;
import com.emrissol.event.DigitActionListener;
import com.emrissol.event.operator.ClearAllActionListener;
import com.emrissol.event.operator.EqualOperatorActionListener;
import com.emrissol.event.operator.PostOperatorActionListener;
import com.emrissol.event.operator.SqrtActionListener;
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

        JButton jButtonClear = operatorFactory.create("C");
        jButtonClear.setActionCommand(Operation.CLEAR.getText());
        jButtonClear.addActionListener(new ClearAllActionListener(manager, uiManager));
        jPanel.add(jButtonClear);

        JButton jButtonPercent = operatorFactory.create("%");
        jButtonPercent.setActionCommand(Operation.PERCENT.getText());
        jPanel.add(jButtonPercent);

        JButton jButtonRoot = operatorFactory.create("root");
        jButtonRoot.setActionCommand(Operation.SQRT.getText());
        jButtonRoot.addActionListener(new SqrtActionListener(manager, uiManager));
        jPanel.add(jButtonRoot);

        JButton jButtonMultiply = operatorFactory.create("*");
        jButtonMultiply.setActionCommand(Operation.MULTIPLY.getText());
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

        JButton jButtonDivide = operatorFactory.create("/");
        jButtonDivide.setActionCommand(Operation.DIVIDE.getText());
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

        JButton jButtonSubstruct = operatorFactory.create("-");
        jButtonSubstruct.setActionCommand(Operation.SUBSTRUCT.getText());
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

        JButton jButtonAdd = operatorFactory.create("+");
        jButtonAdd.setActionCommand(Operation.ADD.getText());
        jButtonAdd.addActionListener(new PostOperatorActionListener(manager, uiManager, Operation.ADD));
        jPanel.add(jButtonAdd);

        JButton jButtonNegative = operatorFactory.create("+/-");
        jButtonNegative.setActionCommand(Operation.NEGATIVE.getText());
        jPanel.add(jButtonNegative);

        JButton jButtonPoint = operatorFactory.create(".");
        jButtonPoint.setActionCommand(Operation.POINT.getText());
        jButtonPoint.setActionCommand(".");
        jPanel.add(jButtonPoint);

        JButton jButton0 = digitFactory.create("0");
        jButton0.setActionCommand("0");
        jPanel.add(jButton0);

        JButton jButtonEqual = new JButtonDigitFactory(Collections.emptyList()).create("=");
        jButtonEqual.setActionCommand(Operation.EQUALS.getText());
//        jButtonEqual.addActionListener(new OperatorActionListener(manager, this));
//        jButtonEqual.addActionListener( actionEvent -> uiManager.getJTextField().setText(""));
        jButtonEqual.addActionListener(new EqualOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonEqual);
    }

}
