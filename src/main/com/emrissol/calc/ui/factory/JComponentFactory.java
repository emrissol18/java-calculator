package com.emrissol.calc.ui.factory;

import javax.swing.*;

public interface JComponentFactory {
     <T extends JComponent> T create(String text);
}
