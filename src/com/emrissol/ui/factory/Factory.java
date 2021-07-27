package com.emrissol.ui.factory;

import javax.swing.*;

public interface Factory {
     <T extends JComponent> T create(String text);
}
