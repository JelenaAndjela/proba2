import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class Bayes {

    private static int likelihood_of_attack = 0;
    private static int typical_severity = 0;
    private static int skills_required = 0;
    private static int authentication = 0;
    private static int suspsuspitious_mail = 0;
    private static int password_policy = 0;
    private static int link_clicked = 0;
    private static int authorization = 0;
    private static int download_free_content = 0;
    private static int server_version = 0;
    private static int vaidation = 0;
 /*   private static int small_company = 0;
    private static int installing_malicious_software = 0;
    private static int poor_data_storage = 0;*/


    public ProbabilisticNetwork load() {
        // loading from file
        String fileName = "data/bayesAJI.net";
        ProbabilisticNetwork net = new ProbabilisticNetwork("bayesAJI");
        BaseIO io = new NetIO();
        try {
            net = (ProbabilisticNetwork) io.load(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (net == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return null;
        }

        // compiling
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        return net;
    }
    public void evidence(ProbabilisticNetwork net){

        // adding an evidence
        ProbabilisticNode factNode1 = (ProbabilisticNode)net.getNode("likelihood_of_attack");
        ProbabilisticNode factNode2 = (ProbabilisticNode)net.getNode("typical_severity");
        ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("skills_required");
        ProbabilisticNode factNode4 = (ProbabilisticNode)net.getNode("authentication");
        ProbabilisticNode factNode5 = (ProbabilisticNode)net.getNode("suspsuspitious_mail");
        ProbabilisticNode factNode6 = (ProbabilisticNode)net.getNode("password_policy");
        ProbabilisticNode factNode7 = (ProbabilisticNode)net.getNode("link_clicked");
        ProbabilisticNode factNode8 = (ProbabilisticNode)net.getNode("authorization");
        ProbabilisticNode factNode9 = (ProbabilisticNode)net.getNode("download_free_content");
        ProbabilisticNode factNode10 = (ProbabilisticNode)net.getNode("server_version");
        ProbabilisticNode factNode11 = (ProbabilisticNode)net.getNode("vaidation");
       /* ProbabilisticNode factNode12 = (ProbabilisticNode)net.getNode("Small_Company");
        ProbabilisticNode factNode13 = (ProbabilisticNode)net.getNode("Installing_malicious_software");
        ProbabilisticNode factNode14 = (ProbabilisticNode)net.getNode("Poor_Data_Storage");*/

        //     System.out.println("RADI LI OVO NEŠTO  ");


        factNode1.addFinding(likelihood_of_attack, true);
        factNode2.addFinding(typical_severity,true);
        factNode3.addFinding(skills_required,true);
        factNode4.addFinding(authentication,true);
        factNode5.addFinding(suspsuspitious_mail,true);
        factNode6.addFinding(password_policy,true);
        factNode7.addFinding(link_clicked,true);
        factNode8.addFinding(authorization,true);

        factNode9.addFinding(download_free_content,true);
        factNode10.addFinding(server_version,true);
        factNode11.addFinding(vaidation,true);
       /* factNode12.addFinding(small_company,true);
        factNode13.addFinding(installing_malicious_software,true);
        factNode14.addFinding(poor_data_storage,true);*/

        // propagation
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // states overview after propagation
        List<Node> nodeList = net.getNodes();
        List<Node> nodeListLief = new ArrayList<>();
        for(Node nodeLief: nodeList){
            if (nodeLief.getChildren().isEmpty() == true) {
                nodeListLief.add(nodeLief);
            }
        }
        Collections.sort(nodeListLief, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(((ProbabilisticNode) o1).getMarginalAt(0) > ((ProbabilisticNode) o2).getMarginalAt(0)){
                    return -1;
                }else if(((ProbabilisticNode) o1).getMarginalAt(0) < ((ProbabilisticNode) o2).getMarginalAt(0)) return 1;
                else return 0;
            }
        });
        DecimalFormat df = new DecimalFormat("###.###");
        String max1 = nodeListLief.get(0).getName() + " " + ": " + df.format(((ProbabilisticNode) nodeListLief.get(0)).getMarginalAt(0)*100)+"%";
        String max2 = nodeListLief.get(1).getName() + " " + ": " + df.format(((ProbabilisticNode) nodeListLief.get(1)).getMarginalAt(0)*100)+"%";
        String max3 = nodeListLief.get(2).getName() + " " + ": " + df.format(((ProbabilisticNode) nodeListLief.get(2)).getMarginalAt(0)*100)+"%";

        viewResult(max1,max2,max3);

        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Bayes(){
        ProbabilisticNetwork net = load();

        GridLayout gl = new GridLayout(14,2);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(gl);
        panel.setBorder(BorderFactory.createEmptyBorder(30,25,30,25));
        String[] lowStrings = {"low","medium","high"};
        String[] noStrings = {"yes","no"};
        String[] validat = {"correct","incorrect"};
        String[] veryHighStrings = {"low","medium","high","very high"};
        String[] correctStrings = {"correct","incorrect"};
        String[] weakStrings = {"weak","medium","strong"};
        String[] oldStrings = {"very old","old","latest"};

        JLabel labelLikelihood_of_attack = new JLabel("Likelihood of attack: ");
        labelLikelihood_of_attack.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboLikelihood_of_attack = new JComboBox(lowStrings);
        comboLikelihood_of_attack.setFont(new Font("Arial", Font.PLAIN, 15));


        JLabel labelTypicalSeverity = new JLabel("Typical severity: ");
        labelTypicalSeverity.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboTypicalSeverity = new JComboBox(veryHighStrings);
        comboTypicalSeverity.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelSkillsRequired = new JLabel("Skills required: ");
        labelSkillsRequired.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboSkillsRequired = new JComboBox(lowStrings);
        comboSkillsRequired.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelAuthentication = new JLabel("Authentication: ");
        labelAuthentication.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboAuthentication = new JComboBox(correctStrings);
        comboAuthentication.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelSuspsuspitiousMail= new JLabel("Suspsuspitious Mail: ");
        labelSuspsuspitiousMail.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboSuspsuspitiousMail = new JComboBox(lowStrings);
        comboSuspsuspitiousMail.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelPasswordPolicy = new JLabel("Password policy: ");
        labelPasswordPolicy.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboPasswordPolicy = new JComboBox(weakStrings);
        comboPasswordPolicy.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelLinkClicked = new JLabel("Link clicked: ");
        labelLinkClicked.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboLinkClicked = new JComboBox(noStrings);
        comboLinkClicked.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelAuthorization = new JLabel("Authorization: ");
        labelAuthorization.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboAuthorization = new JComboBox(lowStrings);
        comboAuthorization.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelDownloadFreeContent = new JLabel("Downloaded free content: ");
        labelDownloadFreeContent.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboDownloadFreeContent = new JComboBox(noStrings);
        comboDownloadFreeContent.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelServerVersion = new JLabel("Server version: ");
        labelServerVersion.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboServerVersion = new JComboBox(oldStrings);
        comboServerVersion.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel labelVaidation = new JLabel("Vaidation: ");
        labelVaidation.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox comboVaidation = new JComboBox(validat);
        comboVaidation.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel prazno = new JLabel(" ");
        JLabel prazno2 = new JLabel("    ");

        JButton calculate = new JButton("Results");
        calculate.setFont(new Font("Arial", Font.PLAIN, 15));
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                likelihood_of_attack = comboLikelihood_of_attack.getSelectedIndex();
                typical_severity = comboTypicalSeverity.getSelectedIndex();
                skills_required = comboSkillsRequired.getSelectedIndex();
                authentication = comboAuthentication.getSelectedIndex();
                suspsuspitious_mail = comboSuspsuspitiousMail.getSelectedIndex();
                password_policy = comboPasswordPolicy.getSelectedIndex();
                link_clicked = comboLinkClicked.getSelectedIndex();
                authorization = comboAuthorization.getSelectedIndex();
                download_free_content = comboDownloadFreeContent.getSelectedIndex();;
                server_version = comboServerVersion.getSelectedIndex();
                vaidation = comboVaidation.getSelectedIndex();

                evidence(net);
                frame.dispose();
            }
        });


        panel.add(labelLikelihood_of_attack);
        panel.add(comboLikelihood_of_attack);

        panel.add(labelTypicalSeverity);
        panel.add(comboTypicalSeverity);

        panel.add(labelSkillsRequired);
        panel.add(comboSkillsRequired);

        panel.add(labelAuthentication);
        panel.add(comboAuthentication);

        panel.add(labelSuspsuspitiousMail);
        panel.add(comboSuspsuspitiousMail);

        panel.add(labelPasswordPolicy);
        panel.add(comboPasswordPolicy);

        panel.add(labelLinkClicked);
        panel.add(comboLinkClicked);

        panel.add(labelAuthorization);
        panel.add(comboAuthorization);

        panel.add(labelDownloadFreeContent);
        panel.add(comboDownloadFreeContent);

        panel.add(labelServerVersion);
        panel.add(comboServerVersion);

        panel.add(labelVaidation);
        panel.add(comboVaidation);

        panel.add(prazno);
        panel.add(prazno2);

        panel.add(prazno);
        panel.add(calculate);

        frame.setPreferredSize(new Dimension(500, 600));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Bayes calculator");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void viewResult(String max1, String max2, String max3){
        GridLayout gl = new GridLayout(3,1);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(gl);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        JLabel result1 = new JLabel("First result: "+max1);
        result1.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel result2 = new JLabel("Second result: "+max2);
        result2.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel result3 = new JLabel("Third result: "+max3);
        result3.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(result1);
        panel.add(result2);
        panel.add(result3);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Bayes");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
