package com.emrissol.calc.ui;

import com.emrissol.calc.Manager;
import com.emrissol.calc.expression.Expression;
import com.emrissol.calc.expression.operation.SimplePostOperation;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class UIManager {

    // wrap html start & end for whole content
    private static String HTML_START = "<html>";
    private static String HTML_END = "</html>";

    private StringBuilder stringBuilderOuter = new StringBuilder(HTML_START + HTML_END);
    private StringBuilder stringBuilderInner = new StringBuilder();

    // Short id -> String layout
    private LinkedHashMap<Short, String> expressionsLayouts = new LinkedHashMap<>(4);

    @Getter
    private Manager manager;

//    @Getter
//    private JTextField jTextField;
    @Getter
    private JLabel jLabel = new JLabel();

    public UIManager(Manager manager) {
        this.manager = manager;
        jLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        jLabel.setPreferredSize(new Dimension(0, 32));
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void createLayout() {
        // 4col x 7row
        MigLayout migLayout = new MigLayout(
                "wrap 4",
                "",
                "");

        JPanel jPanel = new JPanel(migLayout);
//        jPanel.add(createTextArea(), "span 4, pushx, growx, wrap");
//        jPanel.add(getJTextFieldLayout(), "span 4, pushx, growx, wrap");
        jPanel.add(jLabel, "span 4, pushx, growx, height :60:, wrap");

        ButtonCreator buttonCreator = new ButtonCreator(manager, this);
        buttonCreator.createDigitButtons(jPanel);

        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void addText(String text) {
        stringBuilderInner.append(text);
        aggregateInnerSB();
    }

    /*public void refreshLayout(Expression expression) {
        int sbInnerLength = stringBuilderInner.length();
        System.out.println(stringBuilderInner);
        int offset = sbInnerLength - expression.getLength();

        // get layout and recalculate length (expansion case)
        String newLayout = expression.getLayout();

        System.out.println("sbInnerLength = " + sbInnerLength);
        System.out.println("PREV expression.getLength() = " + expression.getLength());
//        System.out.println("offset = " + offset);
//        stringBuilderInner.delete(offset, sbInnerLength);
        if (offset < 0) {
            Logger.log(UIManager.class, "offset is " + offset);
//            Logger.log(UIManager.class, expression.toString());
            offset = Math.abs(offset) + sbInnerLength;
        }
        stringBuilderInner.setLength(offset);
        stringBuilderInner.append(newLayout);
        aggregateInnerSB();
    }

    public void refreshLayout() {
        stringBuilderInner.setLength(0);
        if ( manager.hasExpressions()) {
            Iterator<Expression> expressionIterator = manager.getExpressionQueue().iterator();
            while (expressionIterator.hasNext()) {
                Expression expression = expressionIterator.next();
                stringBuilderInner.append(expression.getLayout());
            }
        }
        aggregateInnerSB();
    }*/


    public String getLayouts() {
        StringBuilder stringBuilder = new StringBuilder();
        if (expressionsLayouts.isEmpty()) {
            return "";
        }
        expressionsLayouts.values().forEach(  (layout) -> stringBuilder.append(layout));
        return stringBuilder.toString();
    }

    public void refreshLayout(Expression expression) {
        // put or replace new layout
        expressionsLayouts.put(expression.getId(), expression.getLayout());

        stringBuilderInner.setLength(0);
        stringBuilderInner.append(getLayouts());

        aggregateInnerSB();
    }

    public void refreshExpressionsLayouts() {
        expressionsLayouts.clear();
        for (Expression expression : manager.getExpressionQueue()) {
            expressionsLayouts.put(expression.getId(), expression.getLayout());
        }
    }

    public void refreshLayout() {
        refreshExpressionsLayouts();
        stringBuilderInner.setLength(0);
        if ( manager.hasExpressions()) {
            expressionsLayouts.values().forEach( (layout) -> stringBuilderInner.append(layout));
        }
        aggregateInnerSB();
    }

    public void setText(Expression expression) {
        stringBuilderInner.setLength(0);
        stringBuilderInner.append(expression.getLayout());
        aggregateInnerSB();
    }

    public void addText(Expression expression) {
        stringBuilderInner.append(expression.getLayout());
        aggregateInnerSB();
    }

    public void changeSign(SimplePostOperation operation) {
        stringBuilderInner.setLength(stringBuilderInner.length() - 1);
        addText(operation);
    }

    public void addText(SimplePostOperation operation) {
        addText(operation.getText());
    }

    public boolean hasText() {
        return stringBuilderInner.length() > 0;
    }

    public void insertText(String text) {
        stringBuilderOuter.replace(getStartOffset(), getEndOffset(), text);
    }

    public void aggregateInnerSB() {
        stringBuilderOuter.replace(getStartOffset(), getEndOffset(), stringBuilderInner.toString());
        jLabel.setText(stringBuilderOuter.toString());
    }

    private int getStartOffset(){
        return HTML_START.length();
    }

    private int getEndOffset(){
        return stringBuilderOuter.length() - HTML_END.length();
    }

    public void removeLastChar() {
        stringBuilderInner.deleteCharAt(stringBuilderInner.length() - 1);
    }

    public void removeCharsByLength(int preOperLength) {
        int length = stringBuilderInner.length();
        stringBuilderInner.delete(length - preOperLength, length);
    }

    public void clearAll() {
        getJLabel().setText("");
        stringBuilderInner.setLength(0);
        stringBuilderOuter.replace(getStartOffset(), getEndOffset(), "");
        expressionsLayouts.clear();
    }

    /*private JTextArea createTextArea() {
        JTextArea jTextArea = new JTextArea("", 6, 0);
        jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        jTextArea.setAutoscrolls(true);
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.LIGHT_GRAY);
        return jTextArea;
    }*/

}
