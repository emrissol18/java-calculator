package com.emrissol.event;

import com.emrissol.Manager;
import com.emrissol.ui.UIManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// action listener for digit buttons
public class DigitActionListener implements ActionListener {

    private Manager manager;
    private UIManager uiManager;

    public DigitActionListener(Manager manager, UIManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton target = (JButton) actionEvent.getSource();
        manager.addToValueAcc(target.getText());
        uiManager.addToTextFieldText(target.getText());
    }

}
