import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;



public class CbrTable {
    public CbrTable(List<Attack> attackList){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        String[] columnNames = {"Click on attack to see similar attacks"};

        String[][] data = new String[attackList.size()][columnNames.length];
        for(int i=0; i<attackList.size(); i++){
                data[i]= new String[]
                        {attackList.get(i).name};

        }

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(data, columnNames);
        JTable table = new JTable(dm);
 
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					int row =table.getSelectedRow();
					new Cbr(attackList.get(row));
				}
				
			}
		});

        table.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        

       frame.add(sp);
        //frame.add(register);
        // Frame Size
        frame.setSize(800, 400);
        // Frame Visible = true
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}
