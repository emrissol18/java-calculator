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

    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    
    // wrap html start & end for whole content
    private static String HTML_START = "<html><body style='overflow-x: hidden;'>";
    private static String HTML_END = "</body></html>";

    private StringBuilder stringBuilderOuter = new StringBuilder(HTML_START + HTML_END);
    private StringBuilder stringBuilderInner = new StringBuilder();

    // Short id -> String layout
    private LinkedHashMap<Short, String> expressionsLayouts = new LinkedHashMap<>(4);

    @Getter
    private Manager manager;

    @Getter
    private JLabel jLabel = new JLabel();

    @Getter
    private ScrollPane scrollPane = new ScrollPane();

    @Getter
    private JPanel jPanelLabel;

    @Getter
    private JPanel jPanelBtns;

    public UIManager(Manager manager) {
        this.manager = manager;
        createLayout();
    }

    public void createLayout() {
        jPanelLabel = new JPanel(new MigLayout("", "[grow, center]", "[]"));
        jLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scrollPane.add(jLabel);
        jPanelLabel.add(scrollPane, "growx, w 100::"); // here set set scrollPane minWidth to 100, otherwise scrollPane won't shrink on resize

        jPanelBtns = new JPanel(new MigLayout("wrap 4","","")); // 4 components for each row
        ButtonCreator buttonCreator = new ButtonCreator(manager, this);
        buttonCreator.createButtons(jPanelBtns);

        JPanel jPanelWrap = new JPanel(new MigLayout("wrap 1", "[grow, center]", "[center][center]"));
        jPanelWrap.add(jPanelLabel, "pushx, growx");
        jPanelWrap.add(jPanelBtns);

        JFrame jFrame = new JFrame("Calculator");
        jFrame.add(jPanelWrap);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void addText(String text) {
        stringBuilderInner.append(text);
        aggregateInnerSB();
    }

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

    /**
     * Try to scroll scrollPane to end if <code>jLabel</code>'s width greater than <code>scrollPane</code>'s viewport.
     */
    public void scrollToEnd() {
        scrollPane.validate();
        int labelWidth = (int) jLabel.getPreferredSize().getWidth();
        if (labelWidth >= scrollPane.getWidth()) {
            scrollPane.setScrollPosition(labelWidth, 0);
        }
    }

}
