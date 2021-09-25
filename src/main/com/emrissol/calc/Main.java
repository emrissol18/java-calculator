package com.emrissol.calc;


import com.emrissol.calc.ui.UIManager;

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
        Manager manager = Manager.getInstance();
        UIManager uiManager = new UIManager(manager);

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

    private static int fz = 20;
    public static String getLabelText(String text) {
        String sbStart = getSbStart(fz);
        String sbEnd = "</body></html>";
        return sbStart.concat(text).concat(sbEnd);
    }

    private static String getSbStart(float fz) {
//        return "<html><body style='font-size:" + fz + "px'>";
        return "<html><body>";
    }

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
}
