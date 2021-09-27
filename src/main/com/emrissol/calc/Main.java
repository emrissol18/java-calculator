package com.emrissol.calc;


import com.emrissol.calc.ui.UIManager;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    /*static StringBuilder stringBuilder = new StringBuilder("<html></html>");
    static StringBuilder stringBuilderEnd = new StringBuilder();
    static StringBuilder stringBuilderInner = new StringBuilder();
    static Map<Integer, String> endGrey = new HashMap<>();
    static {
        endGrey.put(1, ")");
        endGrey.put(2, ")");
    }*/

    public static void main(String[] args) {
        /*try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Manager manager = Manager.getInstance();
        SwingUtilities.invokeLater( () -> {
            UIManager uiManager = new UIManager(manager);
        });

        /*Double nD = 0.12345;
        String n = nD.toString();
        float remainder = (float) Math.abs(nD - nD.intValue());

        int length = String.valueOf(remainder).length()-1;
        System.out.println("length = " + length);

        char lastchar = n.charAt(length);

        while (lastchar == '0' && lastchar != '.') {
            System.out.println("lastchar = " + lastchar);
            lastchar = n.charAt(--length);
        }
        System.out.println(length-1);*/

//        jFrame.setResizable(false);

        /*ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPreferredSize(new Dimension(200, 140));
        JLabel label = new JLabel("sad as asdjg lksd kg9 ijakl jlksa jklasjfhgjkl as f231 sfa as");
        scrollPane.add(label);

        JPanel jPanel = new JPanel();
        jPanel.add(scrollPane);
//        jframe.setPreferredSize(new Dimension(200, 50));

        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
//        jFrame.setPreferredSize(new Dimension(400, 200));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        scrollPane.setScrollPosition((int) label.getPreferredSize().getWidth(), scrollPane.getHeight());*/
        /*JPanel jPanel = new JPanel(new MigLayout("wrap 1"));

        String moreText = " more";
        StringBuilder stringBuilder = new StringBuilder("label text");
        JLabel jLabel = new JLabel(getLabelText(stringBuilder.toString()));
//        jLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));


        JButton jButtonBold = new JButton("adapt fz");

        int panelWidth = 200;

//        jLabel.setPreferredSize(new Dimension(10, 20));
        jPanel.add(jLabel, "growx, pushx");
        jPanel.add(jButtonBold);
        jPanel.setPreferredSize(new Dimension(panelWidth, 100));


        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
//        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        FontMetrics fontMetrics = jLabel.getGraphics().getFontMetrics();
//        int moreTextWidth = fontMetrics.stringWidth(moreText);
//        int labelTextWidth = fontMetrics.stringWidth(stringBuilder.toString());

        Font font = new Font("Verdana", Font.BOLD, 20);
        jLabel.setFont(font);

        JLabel hidden = new JLabel(stringBuilder.toString());

        FontReducer fontReducer = new FontReducer(jLabel);

        jButtonBold.addActionListener( (e) -> {

            jLabel.setText(getLabelText(stringBuilder.append("aa").toString()));

            fontReducer.reduceFontSize();
            *//*if (jLabel.getPreferredSize().getWidth() >= jLabel.getWidth()) {
                System.out.println("reduce font");
                fz -= 1;
                jLabel.setFont(font.deriveFont((float)fz));
            }*//*
            *//*stringBuilder.append(moreText);
            jLabel.setText(sbStart.concat(stringBuilder.toString()).concat(sbEnd));
            double prefW = jLabel.getPreferredSize().getWidth();
            if (prefW >= jLabel.getWidth()) {
                System.out.println("prefW >= jLabel.getWidth()");
            }
            System.out.println("prefW = " + prefW);*//*

//            stringBuilder.insert(6, stringBuilderInner.toString());
//            stringBuilder.replace(6, getOffsetEnd(), stringBuilderInner.toString());
//            jLabel.setText(stringBuilder.toString());
        });*/
    }

    /*private static int fz = 20;
    public static String getLabelText(String text) {
        String sbStart = getSbStart(fz);
        String sbEnd = "</body></html>";
        return sbStart.concat(text).concat(sbEnd);
    }

    private static String getSbStart(float fz) {
//        return "<html><body style='font-size:" + fz + "px'>";
        return "<html><body>";
    }*/

    /*static int getOffsetEnd(){
        return stringBuilder.length() - 7;
    }

    static ActionListener getAL(JLabel jLabel) {
//        String[] end = {""};
//        System.out.println("stringBuilderEnd after length 0 = " + stringBuilderEnd.toString());
        return (actionEvent) -> {
            System.out.println("AL called");
//            stringBuilderEnd.setLength(0);
            endGrey.forEach((integer, s) -> {
                System.out.println(s);
                stringBuilderEnd.append("<font color='gray'>");
                stringBuilderEnd.append(s);
                stringBuilderEnd.append("</font>");
            });
            stringBuilder.replace(6, getOffsetEnd(), stringBuilderInner.toString() + stringBuilderEnd.toString());
            jLabel.setText(stringBuilder.toString());
        };
    }*/
    /*public void parser(){

        String rawExp = "2+";
        Pattern pattern = Pattern.compile("([\\\\.]?\\d+[\\\\.]?\\d*)([+-/*])");
        Matcher matcher = pattern.matcher(rawExp);
//        System.out.println("matcher.groupCount() = " + matcher.groupCount());
        Stream<MatchResult> results = matcher.results();
//        System.out.println("results.count() = " + results.count());
        List<MatchResult> resultList = results.collect(Collectors.toList());
        System.out.println("resultList.size() = " + resultList.size());
        for (MatchResult matchResult : resultList) {
            System.out.println("matchResult.groupCount() = " + matchResult.groupCount());
            for (int i = 1; i < matchResult.groupCount()+1; i++) {
                System.out.println("matchResult.group(i) = " + matchResult.group(i));
            }
        }
    }*/
    /*public void setLookAndFeel() {
        for (UIManager.LookAndFeelInfo installedLookAndFeel : UIManager.getInstalledLookAndFeels()) {
            System.out.println("installedLookAndFeel.getName() = " + installedLookAndFeel.getName());
            System.out.println("installedLookAndFeel.getClassName() = " + installedLookAndFeel.getClassName());
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //        String text = "Hello World";
//        AffineTransform affinetransform = new AffineTransform();
//        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
//        Font font = new Font("Tahoma", Font.PLAIN, 12);
//        int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
//        int textheight = (int)(font.getStringBounds(text, frc).getHeight());
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
