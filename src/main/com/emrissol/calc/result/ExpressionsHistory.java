package com.emrissol.calc.result;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

@Getter
public class ExpressionsHistory {

    private JPanel jPanel;

    private JLabel jLabel;

    private ScrollPane scrollPane;

    private LinkedList<String> expressions = new LinkedList<>();

    private boolean active;

    public JPanel initAndGetLayout() {
        jLabel = new JLabel(getAllAsString());

        scrollPane = new ScrollPane();
        scrollPane.add(jLabel);

        jPanel = new JPanel(new MigLayout("", "[grow, center]", "[]"));
        jPanel.add(scrollPane, "growx, w 100::, h 40::100");
        jLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        return jPanel;
    }

    public String getAllAsString() {
        if (expressions.isEmpty()) {
            return "";
        }
        Iterator<String> iterator = expressions.iterator();
        StringBuilder stringBuilder = new StringBuilder(iterator.next());
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
        }
        return stringBuilder.insert(0, "<html>").append("</html>").toString();
    }

    public void refreshHistory() {
        // return if no GUI has been initialized yet
        if (jLabel == null) {
            return;
        }
        jLabel.setText(getAllAsString());
        scrollScrollPane();
    }

    public void scrollScrollPane() {
        System.out.println("scrollScrollPane()");
        scrollPane.validate();
        scrollPane.setScrollPosition(0, (int) jLabel.getPreferredSize().getHeight());
    }

    public void toggleActive() {
        if ((active = ! active)) {
            scrollScrollPane();
        }
    }
}
