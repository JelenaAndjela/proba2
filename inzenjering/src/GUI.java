import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.membership.MembershipFunction;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.LinguisticTerm;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.temporal.ValueRange;
import java.util.ArrayList;

public class GUI {

    private static double risk = 0;
    private static double availability = 0;
    private static double confidentiality = 0;
    private static double integrity = 0;
    private static double scope = 0;
    private static double severity = 0;
    private static double user_interaction = 0;
    private static double complexity = 0;
    public static void viewRisk(double risk){
        GridLayout gl = new GridLayout(1,2);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(gl);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        JLabel labelRisk = new JLabel("Risk: ");
        labelRisk.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel resultRisk = new JLabel(Double.toString(risk));
        panel.add(labelRisk);
        panel.add(resultRisk);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Risk");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void load_fis(){
        // Load from 'FCL' file
        String fileName = "data/template.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Set inputs

        fis.setVariable("availability", availability);
        System.out.println("ovo1");
        System.out.println(availability);
        fis.setVariable("confidentiality", confidentiality);
        fis.setVariable("integrity", integrity);
        fis.setVariable("scope", scope);
        fis.setVariable("user_interaction", user_interaction);
        fis.setVariable("complexity", complexity);
        fis.setVariable("severity", severity);

        // Evaluate
        fis.evaluate();

        // Print ruleSet
        risk = fis.getVariable("risk").getLatestDefuzzifiedValue();
        System.out.println(risk);
    }

    /* public static double scale1(String input){
         if(input.equals("low")){
             return 0;
         }else if(input.equals("medium")){
             return 0.45;
         }else if(input.equals("high")){
             return 0.70;
         }else return 1;
     }
 */
    public static double scale2(String input){
        if(input.equals("unchanged")){
            return 0;
        }else return 100;
    }
    public static double scale6(String input){
        if(input.equals("low")){
            return 10;
        }else return 50;
    }

    public static double scale3(String input){
        if(input.equals("required")){
            return 100;
        }else return 0;
    }

    public static double scale4(String input){
        if(input.equals("low")){
            return 20;
        }else if(input.equals("high")){
            return 70;
        }else return 0;
    }
    public static double scale5(String input) {
        if (input.equals("low")) {
            return 40;
        } else if (input.equals("high")) {
            return 80;
        } else return 0;
    }
    public static double scale1(String input) {
        if (input.equals("low")) {
            return 10;
        } else if (input.equals("medium")) {
            return 45;
        } else return 70;
    }
    public GUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        GridLayout gl = new GridLayout(9,2);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(gl);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));

        String[] availabilityStrings = {"none","low","high"};
        String[] confidentalityStrings = {"none","low","high"};
        String[] integrityStrings = {"changed","unchanged"};
        String[] scopeStrings = {"changed","unchanged"};
        String[] userInteractionStrings = {"none","required"};
        String[] complexityStrings = {"low","high"};
        String[] severityStrings = {"low","medium","high"};


        JLabel labelAvailability = new JLabel("Availability: ");
        labelAvailability.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboAvailability = new JComboBox(availabilityStrings);
        comboAvailability.setFont(new Font("Arial", Font.PLAIN, 15));



        JLabel labelConfidentiality = new JLabel("Confidentiality: ");
        labelConfidentiality.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboConfidentiality = new JComboBox(confidentalityStrings);
        comboConfidentiality.setFont(new Font("Arial", Font.PLAIN, 15));


        JLabel labelIntegrity = new JLabel("Integrity: ");
        labelIntegrity.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboIntegrity = new JComboBox(integrityStrings);
        comboIntegrity.setFont(new Font("Arial", Font.PLAIN, 15));


        JLabel labelScope = new JLabel("Scope: ");
        labelScope.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboScope = new JComboBox(scopeStrings);
        comboScope.setFont(new Font("Arial", Font.PLAIN, 15));


        JLabel labelUserInteraction = new JLabel("User interaction: ");
        labelUserInteraction.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboUserInteraction = new JComboBox(userInteractionStrings);
        comboUserInteraction.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelComplexity = new JLabel("Complexity: ");
        labelComplexity.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboComplexity = new JComboBox(complexityStrings);
        comboComplexity.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelSeverity = new JLabel("Severity: ");
        labelSeverity.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboSeverity = new JComboBox(severityStrings);
        comboSeverity.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel prazno = new JLabel(" ");
        JLabel prazno2 = new JLabel("    ");

        JButton calculate = new JButton("Results");
        calculate.setFont(new Font("Arial", Font.PLAIN, 15));

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                availability = scale3(comboAvailability.getSelectedItem().toString());
                confidentiality = scale5(comboConfidentiality.getSelectedItem().toString());
                integrity = scale2(comboIntegrity.getSelectedItem().toString());
                integrity = scale3(comboIntegrity.getSelectedItem().toString());
                user_interaction = scale3(comboUserInteraction.getSelectedItem().toString());
                complexity = scale6(comboComplexity.getSelectedItem().toString());
                scope = scale2(comboScope.getSelectedItem().toString());
                severity = scale1(comboSeverity.getSelectedItem().toString());

                load_fis();
                viewRisk(risk);
            }
        });


        panel.add(labelAvailability);
        panel.add(comboAvailability);

        panel.add(labelConfidentiality);
        panel.add(comboConfidentiality);

        panel.add(labelIntegrity);
        panel.add(comboIntegrity);

        panel.add(labelScope);
        panel.add(comboScope);

        panel.add(labelUserInteraction);
        panel.add(comboUserInteraction);

        panel.add(labelComplexity);
        panel.add(comboComplexity);

        panel.add(labelSeverity);
        panel.add(comboSeverity);

        panel.add(prazno);
        panel.add(prazno2);

        panel.add(prazno);

        panel.add(calculate);
        frame.setPreferredSize(new Dimension(500, 600));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Fuzzy");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


}