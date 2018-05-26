package org.spring.sparql.external;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;
import org.spring.sparql.services.SPARQLService;

public class DBPediaController {

    private String URL = "http://dbpedia.org/sparql";
    public void getInfoAboutBand() throws Exception{
        String QUERY = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX dbp: <http://dbpedia.org/resource/>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "\n" +
                "select ?band\n" +
                "where {\n" +
                "\t?band foaf:name \"Rammstein\"@en\n" +
                "}\n";
        SPARQLService sparql = new SPARQLService(URL);

        TupleQueryResult result = sparql.select(QUERY);

        while (result.hasNext()) {
            BindingSet bs = result.next();
            Value band = bs.getValue("band");
            System.out.println("band = " + band.stringValue());
        }
    }
}
