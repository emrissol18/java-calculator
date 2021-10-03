package com.emrissol.calc.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UiManagerJMenuBar extends JMenuBar {

    private JMenu main = new JMenu("main");

    public UiManagerJMenuBar() {
        JMenuItem jmiExit = new JMenuItem("exit");
        jmiExit.addActionListener( (e) -> System.exit(0));
        main.add(jmiExit);
        add(main);
    }

    public void addMenuItem(JMenuItem jmi, ActionListener actionListener) {
        jmi.addActionListener(actionListener);
        main.add(jmi, 0);
    }
}
