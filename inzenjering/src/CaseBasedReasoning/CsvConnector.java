package CaseBasedReasoning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnector  implements Connector  {
	
	public static ArrayList<Model> attacks;
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
	
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/napadiAJI.csv")));
			if (br == null)
				throw new Exception("Error opening file");
		//	System.out.println(" CSVCONNECTOOOOOOOORRRR cita se dobro iz baze" );


			String line = "";
			while ((line = br.readLine()) != null) {
				
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");
	//			System.out.println("CSVCONNECTOOOOOOOORRRR "+ values.length);

				CBRCase cbrCase = new CBRCase();

				Model txDescription = new Model();
				
				
				
				txDescription.setName(values[0]);
			//	System.out.println("CSVCONNECTOOOOOOOORRRR Ime jeee   "+ values[0]);
				txDescription.setLikelihood(values[1]);
				txDescription.setSeverity(values[2]);
				txDescription.setSkills(values[3]);
				txDescription.setPrerequisites(values[4]);
				txDescription.setMitigations(values[5]);
		//		System.out.println("CSVCONNECTOOOOOOOORRRR "+ txDescription);
		//		System.out.println("CSVCONNECTOOOOOOOORRRR "+ values[0]+ values[1]+ values[2]+ values[3]+ values[4]+ values[5]);


				cbrCase.setDescription(txDescription);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
	}
	
	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
	}

	public static ArrayList<Model> getAttacks() {
		return attacks;
	}

	public static void setAttacks(ArrayList<Model> attacks) {
		CsvConnector.attacks = attacks;
	}
	
	

}
