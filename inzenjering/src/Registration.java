import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {


    public Attack a;


    public Registration(){
        //  GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        GridLayout layout = new GridLayout(14,3);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));

        String[] severityStrings = {"low","medium","high","very high"};
        String[] likelihoodStrings = {"low","medium","high"};
        String[] skillsString = {"none", "low","medium", "high"};

        //attack name
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelName = new JLabel("Attack name: ");
        labelName.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelName,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        //gbc.gridwidth = 3;
        JTextField textName = new JTextField();
        textName.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(textName,gbc);

//prazna1
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel prazna1= new JLabel(" ");
        prazna1.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna1,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel prazna12 = new JLabel(" ");
        prazna12.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna12,gbc);

        //likelihood_of_attack
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelLikelihood  = new JLabel("Likelihood of attack: ");
        labelLikelihood.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelLikelihood ,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 150;
        JComboBox likelihood = new JComboBox(likelihoodStrings);
        likelihood.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(likelihood,gbc);

//prazna2
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel prazna2 = new JLabel(" ");
        prazna2.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna2,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JLabel prazna21 = new JLabel(" ");
        prazna21.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna21,gbc);

        //typical_severity
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel labelSeverity = new JLabel("Severity: ");
        labelSeverity.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelSeverity,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipadx = 100;
        JComboBox typical_severity = new JComboBox(severityStrings);
        typical_severity.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(typical_severity,gbc);

        //prazna3
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel prazna3 = new JLabel(" ");
        panel.add(prazna3,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 5;
        JLabel prazna32 = new JLabel(" ");
        panel.add(prazna32,gbc);

        //skills_required
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel labelScope = new JLabel("Skills required: ");
        labelScope.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelScope,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.ipadx = 100;
        JComboBox comboSkills = new JComboBox(skillsString);
        comboSkills.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(comboSkills,gbc);

        //prazna4
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel prazna4 = new JLabel(" ");
        prazna4.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna4,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 7;
        JLabel prazna42 = new JLabel(" ");
        prazna42.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna42,gbc);

        //prerequisites
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel labelImpact = new JLabel("Prerequisites:");
        labelImpact.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelImpact,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.ipadx = 100;
        JComboBox comboPrerequisites = new JComboBox(likelihoodStrings);
        comboPrerequisites.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(comboPrerequisites,gbc);

        //prazna5
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 9;
        JLabel prazna5 = new JLabel(" ");
        panel.add(prazna5,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 9;
        JLabel prazna52 = new JLabel(" ");
        panel.add(prazna52,gbc);


        //mitigations
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 10;
        JLabel labelMitigation = new JLabel("Mitigations: ");
        labelMitigation.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelMitigation,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.ipadx = 150;
        //  gbc.gridwidth = 3;
        JTextField textMitigaiton = new JTextField();
        textMitigaiton.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(textMitigaiton,gbc);

        //prazna
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 11;
        JLabel prazna11 = new JLabel(" ");
        prazna11.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna11,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 11;
        JLabel prazna112 = new JLabel(" ");
        prazna112.setFont(new Font("Arial", Font.PLAIN, 5));
        panel.add(prazna112,gbc);


        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        // gbc.weightx = 0.0;
        //JLabel prazna21 = new JLabel(" ");
        JLabel calculate2 = new JLabel(" ");
        // calculate2.setContentAreaFilled(true);
        panel.add(calculate2,gbc);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        // gbc.weightx = 0.0;
        JButton calculate = new JButton("Add");
        calculate.setContentAreaFilled(true);
        calculate.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(calculate,gbc);

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textName.getText().length()==0){
                    JOptionPane.showMessageDialog(frame,
                            "Attack name can't be empty.",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(textMitigaiton.getText().length()==0){
                    JOptionPane.showMessageDialog(frame,
                            "Attack mitigation can't be empty.",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(textName.getText().length()!=0 && textMitigaiton.getText().length()!=0){

                    //         System.out.println("da l uzme ovaj napad u registration");

                    Attack attack = new Attack(textName.getText(),
                            likelihood.getSelectedItem().toString(),
                            textMitigaiton.getText(),
                            comboPrerequisites.getSelectedItem().toString(),
                            comboSkills.getSelectedItem().toString(),
                            typical_severity.getSelectedItem().toString()
                    );
                    //         System.out.println("da l dodje ovde u registration");
                    RemoteDatabase.insertQuery(attack);
                    a=attack;

                    JOptionPane.showMessageDialog(frame,
                            "Attack successfully registered.",
                            "Success message",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });



        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(500, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Add new attack");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public Attack getAtt(Attack a) {
        return a;
    }

    public Attack getA() {
        return a;
    }

    public void setA(Attack a) {
        this.a = a;
    }



}
