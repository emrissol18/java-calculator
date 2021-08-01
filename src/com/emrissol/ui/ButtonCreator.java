package com.emrissol.ui;

import com.emrissol.Manager;
import com.emrissol.event.DigitActionListener;
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
    private JButtonDigitFactory digitFactory;
    private JButtonOperatorFactory operatorFactory;

    public ButtonCreator(Manager manager, UIManager uiManager) {
        this.manager = manager;
        digitFactory = new JButtonDigitFactory(List.of(new DigitActionListener(manager, uiManager)));
        operatorFactory = new JButtonOperatorFactory(Collections.emptyList());
    }

    public void createDigitButtons(JPanel jPanel) {
        JButton jButton0 = digitFactory.create("0");
        jPanel.add(jButton0, "cell 1 6");
        JButton jButton1 = digitFactory.create("1");
        jPanel.add(jButton1, "cell 0 5");
        JButton jButton2 = digitFactory.create("2");
        jPanel.add(jButton2, "cell 1 5");
        JButton jButton3 = digitFactory.create("3");
        jPanel.add(jButton3, "cell 2 5");
        JButton jButton4 = digitFactory.create("4");
        jPanel.add(jButton4, "cell 0 4");
        JButton jButton5 = digitFactory.create("5");
        jPanel.add(jButton5, "cell 1 4");
        JButton jButton6 = digitFactory.create("6");
        jPanel.add(jButton6, "cell 2 4");
        JButton jButton7 = digitFactory.create("7");
        jPanel.add(jButton7, "cell 0 3");
        JButton jButton8 = digitFactory.create("8");
        jPanel.add(jButton8, "cell 1 3");
        JButton jButton9 = digitFactory.create("9");
        jPanel.add(jButton9, "cell 2 3");
    }

    public void createOperatorButtons(JPanel jPanel) {
        JButton jButtonClear = operatorFactory.create("C");
        jButtonClear.setName(Operation.CLEAR.toString());
        jPanel.add(jButtonClear, "cell 0 2");

        JButton jButtonPercent = operatorFactory.create("%");
        jButtonPercent.setName(Operation.PERCENT.toString());
        jPanel.add(jButtonPercent, "cell 1 2");

        JButton jButtonRoot = operatorFactory.create("root");
        jButtonRoot.setName(Operation.SQRT.toString());
        jButtonRoot.addActionListener(new SqrtActionListener(manager));
        jPanel.add(jButtonRoot, "cell 2 2");
//        JButton jButtonDel = operatorFactory.create("del");
//        jButtonDel.setName(Operation.DEL.toString());
//        jPanel.add(jButtonDel, "cell 2 2");

        JButton jButtonMultiply = operatorFactory.create("*");
        jButtonMultiply.setName(Operation.MULTIPLY.toString());
        jButtonMultiply.addActionListener(new PostOperatorActionListener(manager, Operation.MULTIPLY));
        jPanel.add(jButtonMultiply, "cell 3 2");

        JButton jButtonDivide = operatorFactory.create("/");
        jButtonDivide.setName(Operation.DIVIDE.toString());
        jButtonDivide.addActionListener(new PostOperatorActionListener(manager, Operation.DIVIDE));
        jPanel.add(jButtonDivide, "cell 3 3");

        JButton jButtonSubstruct = operatorFactory.create("-");
        jButtonSubstruct.setName(Operation.SUBSTRUCT.toString());
        jButtonSubstruct.addActionListener(new PostOperatorActionListener(manager, Operation.SUBSTRUCT));
        jPanel.add(jButtonSubstruct, "cell 3 4");

        JButton jButtonAdd = operatorFactory.create("+");
        jButtonAdd.setName(Operation.ADD.toString());
        jButtonAdd.addActionListener(new PostOperatorActionListener(manager, Operation.ADD));
        jPanel.add(jButtonAdd, "cell 3 5");

        JButton jButtonNegative = operatorFactory.create("+/-");
        jButtonNegative.setName(Operation.NEGATIVE.toString());
        jPanel.add(jButtonNegative, "cell 0 6");

        JButton jButtonPoint = operatorFactory.create(".");
        jButtonPoint.setName(Operation.POINT.toString());
        jPanel.add(jButtonPoint, "cell 2 6");
    }

}
