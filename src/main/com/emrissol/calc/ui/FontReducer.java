package com.emrissol.calc.ui;

import javax.swing.*;
import java.util.Objects;

// not used
public class FontReducer {

    private JLabel jLabel;
    private final int minFontSize = 8;

    public FontReducer(JLabel jLabel) {
        this.jLabel = Objects.requireNonNull(jLabel, "Label must not be null!");
    }


    // decrement font size by 1
    public void reduceFontSize() {
        float currentFontSize = jLabel.getFont().getSize();
        if (currentFontSize-1 <= minFontSize) {
            System.out.println("font is already min");
            return;
        }
        if (jLabel.getPreferredSize().getWidth() >= jLabel.getWidth() - 40) {
            System.err.println("reduce font");
            jLabel.setFont(jLabel.getFont().deriveFont(currentFontSize-1));
            reduceFontSize();
        }

    }

}
