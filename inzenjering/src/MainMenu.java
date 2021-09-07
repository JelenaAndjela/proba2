import javax.swing.*;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import CaseBasedReasoning.CsvConnector;
import CaseBasedReasoning.Model;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Properties;


public class MainMenu implements StandardCBRApplication {
	
	
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */
	static CsvConnector ccc=new CsvConnector();
	public static ArrayList<Model> attacks;
	public static JPanel panel;
	NNConfig simConfig;  /** KNN configuration */
	public static JFrame frame;
	
	public static ArrayList<String> res=new ArrayList<String>();

	public void configure() throws ExecutionException {
		_connector =  new CsvConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
	
		
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
       /* private String name;
        private String likelihood;
        private String severity;
        private String skills;
        private String prerequisites;
        private String mitigations;*/

		simConfig.addMapping(new Attribute("likelihood", Model.class), new EqualsStringIgnoreCase());
		simConfig.addMapping(new Attribute("severity", Model.class), new EqualsStringIgnoreCase());
		simConfig.addMapping(new Attribute("skills", Model.class), new EqualsStringIgnoreCase());
		simConfig.addMapping(new Attribute("prerequisites", Model.class), new EqualsStringIgnoreCase());
		//simConfig.addMapping(new Attribute("mitigations", Model.class), new EqualsStringIgnoreCase());
		System.out.println(" sim conffig  "+simConfig);

	}

	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");


		if(res.size()==0) {
			
		}else {
			res.clear();
		}
		
		for (RetrievalResult nse : eval) {		
	//		System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
		    res.add(nse.get_case().getDescription() + " -> " + nse.getEval());
		}
		
		
		
	}

	public void postCycle() throws ExecutionException {
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();

		return _caseBase;}

	
	
    public static void main (String[] args){

        try {
           // UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

            Properties props = new Properties();


            props.put("logoString", "Attacks");
            props.put("backgroundColor", "253 243 246");
         //   props.put("buttonColor", "252 245 253");

            // set your theme
            SmartLookAndFeel.setCurrentTheme(props);

            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

        } catch(Exception ignored){
            System.out.println("Look and feel error");
        }


        GridLayout gl = new GridLayout(12,0);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(gl);
        panel.setBorder(BorderFactory.createEmptyBorder(80,80,30,80));

        JLabel prazno1 = new JLabel(" ");
        JLabel prazno2 = new JLabel(" ");
        JLabel prazno3 = new JLabel(" ");
        JLabel prazno4 = new JLabel(" ");
        JLabel prazno5 = new JLabel(" ");


        JButton fuzzy = new JButton("Fuzzy");
        fuzzy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI();
            }
        });
        JButton bayes = new JButton("Bayes");
        bayes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Bayes();
            }
        });

        

        panel.add(fuzzy);
        panel.add(bayes);

        JButton register = new JButton("Add new attack");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();
            }
        });
        JButton viewAll = new JButton("View all attacks");
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Attack> attacks = new ArrayList<>();
					attacks =	RemoteDatabase.selectAllQuery();
                new AttackTable(attacks);
            }
        });
        JButton cbr = new JButton("View similarity");
        cbr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Attack> attacks = RemoteDatabase.selectAllQuery();
                new CbrTable(attacks);

            }
        });
        JButton  mitigations= new JButton("View mitigations"); 
        mitigations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Attack> attacks = RemoteDatabase.selectAllQuery();
                new MitigationsTable(attacks);

            }
        });
        fuzzy.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(fuzzy);
        panel.add(prazno1);
        bayes.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(bayes);
        panel.add(prazno2);
        register.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(register);
        panel.add(prazno3);
        viewAll.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(viewAll);
        panel.add(prazno4);
        cbr.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(cbr);
        panel.add(prazno5);
        mitigations.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(mitigations);

        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
     //   frame.setSize(screen.width/4*3, screen.height/4*3);
       frame.setLocation(screen.width/2-frame.getSize().width/2, screen.height/2-frame.getSize().height/2);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

       
      
		frame.repaint();
		frame.revalidate();
    }
}
