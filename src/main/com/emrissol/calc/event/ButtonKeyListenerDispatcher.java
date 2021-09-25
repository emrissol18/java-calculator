package com.emrissol.calc.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ButtonKeyListenerDispatcher implements KeyEventDispatcher {

    private Map<Integer, JButton[]> buttonsMap = new HashMap<>(20);

    public ButtonKeyListenerDispatcher() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public ButtonKeyListenerDispatcher(Map<Integer, JButton[]> buttonsMap) {
        this();
        this.buttonsMap = buttonsMap;
    }

    public void addButton(int keyCode, JButton... jButton) {
        buttonsMap.put(keyCode, jButton);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
            Optional<JButton[]> jButtons = Optional.ofNullable(buttonsMap.get(keyEvent.getKeyCode()));
            if (jButtons.isPresent()) {
                for (JButton b : jButtons.get()) {
                    for (ActionListener l : b.getActionListeners()) {
                        l.actionPerformed(new ActionEvent(b, ActionEvent.ACTION_FIRST, b.getActionCommand()));
                    }
                }
            }
        }
        return false;
    }
}
