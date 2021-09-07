import org.apache.jena.query.*;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class RemoteDatabase {

    private static final String QUERY_URL = "http://localhost:3030/database/sparql";
    private static final String UPDATE_URL = "http://localhost:3030/database/update";



    public static void insertQuery(Attack attack) {

        String insertString = ""
                + "PREFIX attacks: <https://github.com/JelenaAndjela/inzenjering2021.git/attacks#> "
                + "INSERT DATA {"
                + " attacks:" + attack.name.replaceAll(" ", "") +"\n"
                + " a attacks:Attack ; "
                + " attacks:name  '" + attack.name + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:likelihood_of_attack  '" + attack.likelihood_of_attack + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:mitigations  '" + attack.mitigations + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:prerequisites  '" + attack.prerequisites + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:skills_required  '" + attack.skills_required + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:typical_severity  '" + attack.typical_severity + "' ^^<http://w3.org/2001/XMLSchema#string>."
                + "}";
        System.out.println(insertString);

        UpdateRequest updateRequest = UpdateFactory.create(insertString);
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
        updateProcessor.execute();
   }

    public static void deleteQuery(Attack attack) {

        String deleteString = ""
                + "PREFIX attacks: <https://github.com/JelenaAndjela/inzenjering2021.git/attacks#> "
                + "DELETE "
                + "WHERE {"
                + " attacks:" + attack.name.replaceAll(" ", "")
                + " a attacks:Attack ; "
                + " attacks:name  '" + attack.name + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:likelihood_of_attack  '" + attack.likelihood_of_attack + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:mitigations  '" + attack.mitigations + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:prerequisites  '" + attack.prerequisites + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:skills_required  '" + attack.skills_required + "' ^^<http://w3.org/2001/XMLSchema#string>;"
                + " attacks:typical_severity  '" + attack.typical_severity + "' ^^<http://w3.org/2001/XMLSchema#string>."
                + "}";
        System.out.println(deleteString);

        UpdateRequest updateRequest = UpdateFactory.create(deleteString);
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
        updateProcessor.execute();

    }

    public static List<Attack>  selectAllQuery() {
        String queryString = ""
            +"PREFIX attacks: <https://github.com/JelenaAndjela/inzenjering2021.git/attacks#>"
            +"SELECT ?x ?name ?likelihood_of_attack ?mitigations ?prerequisites  ?skills_required  ?typical_severity"
                + " WHERE { ?x a attacks:Attack; attacks:name  ?name ; attacks:likelihood_of_attack  ?likelihood_of_attack;"
                + " attacks:typical_severity  ?typical_severity; attacks:skills_required  ?skills_required;"
                + " attacks:prerequisites  ?prerequisites; attacks:mitigations  ?mitigations.}";
      //  System.out.println("OVO DA SE POPRAVI  remotr database nije se popravilo" );



        List<Attack> attackList =  new ArrayList<>();

        Query query = QueryFactory.create(queryString);
        try {
            QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_URL, query);

            ResultSet results = qexec.execSelect() ;
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution() ;
            //    System.out.println(solution.getLiteral("name").getString());
             //   System.out.println(solution.getLiteral("likelihood_of_attack").getString());
              //  System.out.println("ovo je dobro");


                attackList.add(new Attack(solution.getLiteral("name").getString(),
                        solution.getLiteral("likelihood_of_attack").getString(),
                        solution.getLiteral("mitigations").getString(),
                        solution.getLiteral("prerequisites").getString(),
                        solution.getLiteral("skills_required").getString(),
                        solution.getLiteral("typical_severity").getString() ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(Attack a:attackList) {
       //     System.out.println("Svi napadi iz selecta suuuuu" + a.getName() +"mitigations "+a.getMitigations());

        	
        }
        
        return attackList;
    }
}
