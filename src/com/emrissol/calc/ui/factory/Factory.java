package com.emrissol.calc.ui.factory;

import javax.swing.*;

public interface Factory {
     <T extends JComponent> T create(String text);
}
