package com.emrissol;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static StringBuilder stringBuilder = new StringBuilder("<html></html>");
    static StringBuilder stringBuilderEnd = new StringBuilder();
    static StringBuilder stringBuilderInner = new StringBuilder();
    static Map<Integer, String> endGrey = new HashMap<>();
    static {
        endGrey.put(1, ")");
        endGrey.put(2, ")");
    }

    public static void main(String[] args) {
//        Manager manager = Manager.getInstance();
//        new UIManager(manager).createLayout();

        JPanel jPanel = new JPanel(new MigLayout("wrap 1"));

        JLabel jLabel = new JLabel(stringBuilder.toString());
        jLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        JButton jButtonBold = new JButton("bold");
        jButtonBold.addActionListener(getAL(jLabel));
        jButtonBold.addActionListener( (e) -> {
            System.out.println("bold");
            stringBuilderInner.append("<b>bold</b>");
//            stringBuilder.insert(6, stringBuilderInner.toString());
//            stringBuilder.replace(6, getOffsetEnd(), stringBuilderInner.toString());
//            jLabel.setText(stringBuilder.toString());
        });

        JButton jButtonSup = new JButton("sup");
        jButtonSup.addActionListener(getAL(jLabel));
        jButtonSup.addActionListener( (e) -> {
            stringBuilderInner.append("<sup>100</sup>");
//            stringBuilder.replace(6, getOffsetEnd(), stringBuilderInner.toString());
//            jLabel.setText(stringBuilder.toString());
//            System.out.println("jLabel.getText() = " + jLabel.getText());
        });

        jPanel.add(jLabel, "growx, pushx");
        jPanel.add(jButtonBold);
        jPanel.add(jButtonSup);
        jPanel.setPreferredSize(new Dimension(600, 200));

        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    static int getOffsetEnd(){
        return stringBuilder.length() - 7;
    }

    static ActionListener getAL(JLabel jLabel) {
//        String[] end = {""};
//        System.out.println("stringBuilderEnd after length 0 = " + stringBuilderEnd.toString());
        return (actionEvent) -> {
            System.out.println("AL called");
            stringBuilderEnd.setLength(0);
            endGrey.forEach((integer, s) -> {
                System.out.println(s);
//                end[0] += "<font color=#292a2d>" + s + "</font>";
                stringBuilderEnd.append("<font color='gray'>");
                stringBuilderEnd.append(s);
                stringBuilderEnd.append("</font>");
            });

//            System.out.println("stringBuilderInner = " + stringBuilderInner.toString());
//            System.out.println("stringBuilderEnd = " + stringBuilderEnd.toString());

            stringBuilder.replace(6, getOffsetEnd(), stringBuilderInner.toString() + stringBuilderEnd.toString());
//            System.out.println("stringBuilder = " + stringBuilder.toString());
            jLabel.setText(stringBuilder.toString());
//            System.out.println(stringBuilder.toString());
        };
    }
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
}
