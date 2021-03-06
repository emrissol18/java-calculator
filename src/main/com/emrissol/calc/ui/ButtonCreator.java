package com.emrissol.calc.ui;

import com.emrissol.calc.Manager;
import com.emrissol.calc.event.ButtonKeyListenerDispatcher;
import com.emrissol.calc.event.PostOperationActionListenerFactory;
import com.emrissol.calc.event.digit.DigitActionListener;
import com.emrissol.calc.event.digit.PointActionListener;
import com.emrissol.calc.event.operator.*;
import com.emrissol.calc.event.special.ClearAllActionListener;
import com.emrissol.calc.event.special.DelActionListener;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import com.emrissol.calc.expression.operation.layout.OperatorConsts;
import com.emrissol.calc.expression.operation.layout.SpecialOperatorConsts;
import com.emrissol.calc.ui.factory.JButtonDigitFactory;
import com.emrissol.calc.ui.factory.JButtonOperatorFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
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
        operatorFactory = new JButtonOperatorFactory();
    }

    public void createButtons(JPanel jPanel) {
        ButtonKeyListenerDispatcher keyListenerDispatcher = new ButtonKeyListenerDispatcher();

        JButton jButtonClear = operatorFactory.create(OperatorConsts.CLEAR);
        jButtonClear.addActionListener(new ClearAllActionListener(manager, uiManager));
        jPanel.add(jButtonClear, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_DELETE, jButtonClear);

//        JButton jButtonCurrentStatus = operatorFactory.create("S");
//        jButtonCurrentStatus.addActionListener( (e) -> {
//            String cv = null;
//            if (manager.hasCurrent()) {
//                cv = manager.getCurrentExp().getLayout();
//            }
//            System.out.println("current: " + cv);
////            logger.log("current: " + cv);
//        });
//        jPanel.add(jButtonCurrentStatus, "wrap, sg btn");

        JButton jButtonFactorial = operatorFactory.create(SpecialOperatorConsts.FACTORIAL);
        jButtonFactorial.addActionListener(new FactorialOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonFactorial, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_F, jButtonFactorial);

        JButton jButtonPow = operatorFactory.create(SpecialOperatorConsts.POW);
        jButtonPow.addActionListener(new PowOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonPow, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_P, jButtonPow);

        JButton jButtonDel = operatorFactory.create(OperatorConsts.DEL);
        jButtonDel.addActionListener(new DelActionListener(manager, uiManager));
        jPanel.add(jButtonDel, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_BACK_SPACE, jButtonDel);

//        JButton jButtonPercent = operatorFactory.create(OperatorText.PERCENT);
//        jButtonPercent.addActionListener(new PercentActionListener(manager, uiManager));
//        jPanel.add(jButtonPercent, "sg btn, wrap");

        JButton jButtonRoot = operatorFactory.create(SpecialOperatorConsts.SQRT);
        jButtonRoot.addActionListener(new SqrtActionListener(manager, uiManager));
        jPanel.add(jButtonRoot, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_R, jButtonRoot);

        JButton jButtonParentheseLeft = operatorFactory.create(OperatorConsts.PARENTHESES_LEFT);
        jButtonParentheseLeft.addActionListener(new ParentheseLeftActionListener(manager, uiManager));
        jPanel.add(jButtonParentheseLeft, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_OPEN_BRACKET, jButtonParentheseLeft);

        JButton jButtonParentheseRight = operatorFactory.create(OperatorConsts.PARENTHESES_RIGHT);
        jButtonParentheseRight.addActionListener(new ParentheseRightActionListener(manager, uiManager));
        jPanel.add(jButtonParentheseRight, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_CLOSE_BRACKET, jButtonParentheseRight);

        PostOperationActionListenerFactory postOperationFactory = new PostOperationActionListenerFactory(manager, uiManager);

        JButton jButtonMultiply = operatorFactory.create(SimplePostOperation.MULTIPLY.getText());
        jButtonMultiply.addActionListener(postOperationFactory.create(SimplePostOperation.MULTIPLY));
        jPanel.add(jButtonMultiply, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_MULTIPLY, jButtonMultiply);

        JButton jButton7 = digitFactory.create("7");
        jPanel.add(jButton7, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD7, jButton7);

        JButton jButton8 = digitFactory.create("8");
        jPanel.add(jButton8, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD8, jButton8);

        JButton jButton9 = digitFactory.create("9");
        jPanel.add(jButton9, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD9, jButton9);

        JButton jButtonDivide = operatorFactory.create(SimplePostOperation.DIVIDE.getText());
        jButtonDivide.addActionListener(postOperationFactory.create(SimplePostOperation.DIVIDE));
        jPanel.add(jButtonDivide, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_DIVIDE, jButtonDivide);


        JButton jButton4 = digitFactory.create("4");
        jPanel.add(jButton4, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD4, jButton4);

        JButton jButton5 = digitFactory.create("5");
        jPanel.add(jButton5, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD5, jButton5);

        JButton jButton6 = digitFactory.create("6");
        jPanel.add(jButton6, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD6, jButton6);

        JButton jButtonSubstruct = operatorFactory.create(SimplePostOperation.SUBSTRUCT.getText());
        jButtonSubstruct.addActionListener(postOperationFactory.create(SimplePostOperation.SUBSTRUCT));
        jPanel.add(jButtonSubstruct, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_SUBTRACT, jButtonSubstruct);

        JButton jButton1 = digitFactory.create("1");
        jPanel.add(jButton1, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD1, jButton1);

        JButton jButton2 = digitFactory.create("2");
        jPanel.add(jButton2, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD2, jButton2);

        JButton jButton3 = digitFactory.create("3");
        jPanel.add(jButton3, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD3, jButton3);

        JButton jButtonAdd = operatorFactory.create(SimplePostOperation.ADD.getText());
        jButtonAdd.addActionListener(postOperationFactory.create(SimplePostOperation.ADD));
        jPanel.add(jButtonAdd, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_ADD, jButtonAdd);

        JButton jButtonNegative = operatorFactory.create(SpecialOperatorConsts.NEGATIVE);
        jButtonNegative.addActionListener(new NegativeOperatorActionListener(manager, uiManager));
        jPanel.add(jButtonNegative, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_N, jButtonNegative);

        JButton jButtonPoint = operatorFactory.create(SimplePostOperation.POINT.getText());
        jButtonPoint.addActionListener(new PointActionListener(manager, uiManager));
        jPanel.add(jButtonPoint, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_DECIMAL, jButtonPoint);

        JButton jButton0 = digitFactory.create("0");
        jPanel.add(jButton0, "sg btn");
        keyListenerDispatcher.addButton(KeyEvent.VK_NUMPAD0, jButton0);

        digitFactory.setListeners(List.of(new EqualOperatorActionListener(manager, uiManager)));
        JButton jButtonEqual = digitFactory.create(SimplePostOperation.EQUALS.getText());
        keyListenerDispatcher.addButton(KeyEvent.VK_ENTER, jButtonEqual);
        jPanel.add(jButtonEqual, "sg btn");
    }

}
