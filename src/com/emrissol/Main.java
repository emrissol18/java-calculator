package com.emrissol;

import com.emrissol.ui.UIManager;

public class Main {

    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        new UIManager(manager).createLayout();

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
