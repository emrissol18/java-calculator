package com.emrissol.ui;

import com.emrissol.Manager;
import com.emrissol.event.operator.EqualOperatorActionListener;
import com.emrissol.expression.operation.Operation;
import com.emrissol.ui.factory.JButtonDigitFactory;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class UIManager {

    @Getter
    private Manager manager;

    @Getter
    private JTextField jTextField;

    public UIManager(Manager manager) {
        this.manager = manager;
    }

    public void createLayout() {
        // 4col x 7row
        MigLayout migLayout = new MigLayout(
                "debug",
                "40[grow][grow][grow][grow]40",
                "40" +
                        "[]0" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "40");

        JPanel jPanel = new JPanel(migLayout);
        jPanel.add(createTextArea(), "span 4, pushx, growx, wrap");
        jPanel.add(getJTextFieldLayout(), "span 4, pushx, growx, wrap");

        ButtonCreator buttonCreator = new ButtonCreator(manager, this);
        buttonCreator.createDigitButtons(jPanel);
        buttonCreator.createOperatorButtons(jPanel);

        JButton jButtonEqual = new JButtonDigitFactory(Collections.emptyList()).create("=");
        jButtonEqual.setName(Operation.EQUALS.toString());
//        jButtonEqual.addActionListener(new OperatorActionListener(manager, this));
        jButtonEqual.addActionListener( actionEvent -> {
            jTextField.setText("");
        });
        jButtonEqual.addActionListener(new EqualOperatorActionListener(manager));
        jPanel.add(jButtonEqual, "cell 3 6");

        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void addToTextFieldText(String text) {
        jTextField.setText(jTextField.getText() + text);
    }

    private JTextField getJTextFieldLayout() {
        jTextField = new JTextField("");
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        return jTextField;
    }

    private JTextArea createTextArea() {
        JTextArea jTextArea = new JTextArea("", 6, 0);
        jTextArea.setFont(new Font("Monospace", Font.PLAIN, 14));
        jTextArea.setAutoscrolls(true);
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.LIGHT_GRAY);
        return jTextArea;
    }



}
