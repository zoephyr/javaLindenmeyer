package com.kuerkwitz.Lsystem.UI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Project2GUI extends JFrame implements ActionListener {

    private JTextField lhs[], rhs[], angle, startSymbol, initialAngle, lineLength;
    private JButton drawButton;
    private JSpinner iterationSpinner;
    private JLabel ruleLabels[], angleLabel, initialAngleLabel, startLabel, iterationsLabel, lineLengthLabel;
    private ButtonGroup rootSelection;
    private JRadioButton drawRootBottom, drawRootCorner, drawRootCenter;
    private DisplayGraphics canvasPanel;
    static List<Line2D.Double> drawingLines;
    static int rootX = 0;
    static int rootY = 750;

    public Project2GUI () {
        this.setTitle("L-Systems, K. Uerkwitz");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(buildGUI());
        this.pack();
        this.setVisible(true);
    }

    private JPanel buildGUI() {
        // Create Master JPanel
        JPanel masterGUI = new JPanel();

        //Create Element JPanels
        JPanel ButtonsPane = new JPanel();
        JPanel RulesPane = new JPanel();
        JPanel DrawPane = new JPanel();

        //Setup Layout Manager
        BoxLayout layoutManager = new BoxLayout(masterGUI, BoxLayout.Y_AXIS);
        masterGUI.setLayout(layoutManager);

        //Create Buttons and Fields
            //Radio Buttons:
        rootSelection = new ButtonGroup();
        rootSelection.add(drawRootBottom = new JRadioButton("Bottom"));
        rootSelection.add(drawRootCenter = new JRadioButton("Center"));
        rootSelection.add(drawRootCorner = new JRadioButton("Corner"));

            //Labels and Buttons
        initialAngleLabel = new JLabel ("Initial Angle: ");
        initialAngle = new JTextField(4);
        lineLengthLabel = new JLabel ("Line Length ");
        lineLength = new JTextField(4);
        angleLabel = new JLabel ("Angle: ");
        angle = new JTextField (4);
        startLabel = new JLabel ("Start Symbol: ");
        startSymbol = new JTextField(2);
        drawButton = new JButton ("Draw");

            //Iterations Spinner
        iterationsLabel = new JLabel("Iterations: ");
        iterationSpinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));

        //Create Button Listener
        drawButton.addActionListener(this);

        //Setup Canvas to use, uses DisplayGraphics class
        canvasPanel = new DisplayGraphics();
        canvasPanel.setBackground(Color.white);
        canvasPanel.setPreferredSize(new Dimension(900, 750));
        canvasPanel.setBorder(new LineBorder(Color.BLACK));

        //Setup Button and Rules Pane
        ButtonsPane.setPreferredSize(new Dimension(900, 30));
        ButtonsPane.setMaximumSize(new Dimension(900, 50));
        RulesPane.setPreferredSize(new Dimension(900, 30));
        RulesPane.setMaximumSize(new Dimension(900, 50));

        //Add Buttons and Features to panes
        ButtonsPane.add(initialAngleLabel);
        ButtonsPane.add(initialAngle);
        ButtonsPane.add(lineLengthLabel);
        ButtonsPane.add(lineLength);
        ButtonsPane.add(drawRootBottom);
        ButtonsPane.add(drawRootCenter);
        ButtonsPane.add(drawRootCorner);
        ButtonsPane.add(angleLabel);
        ButtonsPane.add(angle);
        ButtonsPane.add(startLabel);
        ButtonsPane.add(startSymbol);
        ButtonsPane.add(iterationsLabel);
        ButtonsPane.add(iterationSpinner);
        ButtonsPane.add(drawButton);

        DrawPane.add(canvasPanel);
        //Populate the Rules bar
        lhs = new JTextField[5];
        rhs = new JTextField[5];
        ruleLabels = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            ruleLabels[i] = new JLabel("Rule "+i+" : ");
            RulesPane.add(ruleLabels[i]);
            lhs[i] = new JTextField(2);
            RulesPane.add(lhs[i]);
            rhs[i] = new JTextField(10);
            RulesPane.add(rhs[i]);
        }

        masterGUI.add(RulesPane);
        masterGUI.add(DrawPane);
        masterGUI.add(ButtonsPane);

        return masterGUI;
    }

    private void errorDialog(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel");
            System.err.println("Using the default look and feel.");
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
            System.err.println("Using the default look and feel.");
        } catch(InstantiationException e){
            System.err.println("Could not instantiate the look and feel");
            System.err.println("Using the default look and feel.");
        }catch (IllegalAccessException e) {
            System.err.println("Illegal access to look and feel.");
            System.err.println("Using the default look and feel.");
        }
        Project2GUI project2 = new Project2GUI();
    }

    private String expandRule(String initialString, int iterations, List<RuleSet> rules){
        return("Hello");
    }

    public void actionPerformed(ActionEvent arg0) {

        // Get the Angle value from UI
        String angleText = angle.getText();
        double angle;
        try {
            angle = Double.parseDouble(angleText);
        } catch (NumberFormatException e) {
            errorDialog("Angle field must be a decimal formatted number.");
            return;
        }
        // Get the Line Length value
        double lineLengthValue;
        try {
            lineLengthValue = Double.parseDouble(lineLength.getText());
        } catch (NumberFormatException e) {
            errorDialog("Line length field must be a decimal formatted number.");
            return;
        }

        // Get the Initial Angle value
        double initialAngleValue;
        try {
            initialAngleValue = Double.parseDouble(initialAngle.getText());
        } catch (NumberFormatException e) {
            errorDialog("Initial angle field must be a decimal formatted number.");
            return;
        }
        // Get the Initial Angle value
        int iterationsValue;
        iterationsValue = (int)iterationSpinner.getValue();

        // Get the Start Symbol Value
        String startSymbolValue = startSymbol.getText();
        if (startSymbolValue.isEmpty()) {
            errorDialog("Start symbol cannot be empty.");
            return;
        }

        // Get the origin start from the radio buttons,
        //TODO Possible Window Resizing
        rootX = drawRootCorner.isSelected() ? 0   : 450;
        rootY = drawRootCenter.isSelected() ? 325 : 749;

        // Get the rules
        ArrayList<String[]> ruleSets = getRuleSets();

        // Expand the String
        //expandRule(String[])

        // Make the Lines
        //TODO line add method
        canvasPanel.repaint();


        //TODO Remove testing Option Message
        JOptionPane.showMessageDialog(null, "The Draw Button Works.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
    private ArrayList<String[]> getRuleSets(){
        ArrayList<String[]> finalRules = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            String[] tempRules = new String[2];
            tempRules[0] = lhs[i].getText();
            tempRules[1] = rhs[i].getText();
            finalRules.add(tempRules);
        }
        return finalRules;
    }
}