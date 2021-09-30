package com.emrissol.calc;


import com.emrissol.calc.ui.UIManager;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Manager manager = Manager.getInstance();
        SwingUtilities.invokeLater( () -> {
            UIManager uiManager = new UIManager(manager);
        });
    }
    /*Executable Java Wrappers
    They take your Java app as input and wrap them in an executable (for a specified platform). 
    You can customize them as you like; and if the user doesn't have 
    Java installed, the download page will open.

    Some examples are Launch4J, JSmooth and Jar2EXE.
     

    Installers
    They are independent applications configured to copy your app files to the user's 
    computer and (optionally) create a shortcut.

    Some installers are written in Java, so they're multiplatform. In this case, the installer is a .jar.
    Some others are platform-dependent, but you have the advantage that you don't need to wrap them.

    Java installers: IzPack, Packlet, PackJacket, Antigen, …*/
}
