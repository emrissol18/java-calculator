package com.emrissol.ui;

import com.emrissol.Manager;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
                "wrap 4",
                "",
                "");

        JPanel jPanel = new JPanel(migLayout);
        jPanel.add(createTextArea(), "span 4, pushx, growx, wrap");
        jPanel.add(getJTextFieldLayout(), "span 4, pushx, growx, wrap");

        ButtonCreator buttonCreator = new ButtonCreator(manager, this);
        buttonCreator.createDigitButtons(jPanel);

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

    public void addBtnTextToTextField(ActionEvent actionEvent) {
        jTextField.setText(jTextField.getText() + actionEvent.getActionCommand());
    }

    public void addBtnTextToTextField(String text) {
        jTextField.setText(jTextField.getText() + text);
    }

    private JTextField getJTextFieldLayout() {
        jTextField = new JTextField("");
        jTextField.setFont(new Font("Monospace", Font.PLAIN, 32));
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



    public void trimTextFieldText() {
        trimTextFieldText(1);
    }

    public void trimTextFieldText(int length) {
        String valG = getJTextField().getText();
        getJTextField().setText(valG.substring(0, valG.length() - length));
    }

}
