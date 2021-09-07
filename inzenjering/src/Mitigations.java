import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Mitigations {
	
	public  ArrayList<Mitigation> resMitigation=new ArrayList<Mitigation>();

	public Mitigations(Attack a) {
		
		    JFrame frame = new JFrame();
	        JPanel panel = new JPanel();

	        String[] columnNames = {"Mitigations"};

	         
			
			if(resMitigation.size()!=0) {
				resMitigation.clear();
			}
			/*
			 * System.out.println("AAAAAAAAAAAA" +a.toString()); String[]
			 * ss=a.toString().split("="); String[] ss1=ss[8].split("]");
			 */
			
				String mitigations=a.getMitigations();
			//	System.out.println("Mitigations  "+a.getMitigations() );
	        // 	System.out.println("Mitigations  "+a.getName() );




		Mitigation m=new Mitigation(mitigations);
			resMitigation.add(m);

			
			   String[][] data = new String[resMitigation.size()][columnNames.length];
		        for(int i=0; i<resMitigation.size(); i++){
		                data[i]= new String[]
		                        { resMitigation.get(i).text};
			
		
	      

	        DefaultTableModel dm = new DefaultTableModel();
	        dm.setDataVector(data, columnNames);
	        JTable table = new JTable(dm);


	        
	        
	        
	        
	        table.setBounds(30, 40, 200, 300);
            
	        // adding it to JScrollPane
	        JScrollPane sp = new JScrollPane(table);
	        table.setPreferredScrollableViewportSize(table.getPreferredSize());
	        table.getColumnModel().getColumn(0).setPreferredWidth(100);
	        

	       frame.add(sp);
	        //frame.add(register);
	        // Frame Size
	        frame.setSize(800, 400);
					frame.setLocationRelativeTo(null);
	        // Frame Visible = true
	        frame.setVisible(true);
		
		
		
	}


}
}