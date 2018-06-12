package org.spring.sparql.service.DBPedia;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;
import org.spring.sparql.utils.QueryFinder;
import org.spring.sparql.entity.ArtistEntity;
import org.spring.sparql.service.SPARQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBPediaController {
    private String URL = "http://dbpedia.org/sparql";

    private SPARQLService sparql;

//    @Autowired
    private QueryFinder queryFinder;

    public DBPediaController() {
        sparql = new SPARQLService(URL);
        queryFinder = new QueryFinder();
    }

    public void getInfoAboutBand() throws Exception {
        String QUERY = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX dbp: <http://dbpedia.org/resource/>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "\n" +
                "select ?band\n" +
                "where {\n" +
                "\t?band foaf:name \"Rammstein\"@en\n" +
                "}\n";

        TupleQueryResult result = sparql.select(QUERY);

        while (result.hasNext()) {
            BindingSet bs = result.next();
            Value band = bs.getValue("band");
            System.out.println("band = " + band.stringValue());
        }
    }

    public List<ArtistEntity> getArtistHometown(String name) throws Exception {
        String query = String.format(queryFinder.find("getArtistHometownLocationQuery"), name);
        TupleQueryResult result = sparql.select(query);
        List<ArtistEntity> artists = new ArrayList<>();
        while (result.hasNext()) {
            BindingSet bs = result.next();
            ArtistEntity artist = new ArtistEntity(name, bs);
            artists.add(artist);
        }
        return artists;
    }

    public List<ArtistEntity> getArtistsHometowns(String[] names) throws Exception {
        String tmp = "";
        for (String name : names) {
            tmp += String.format("(\"%s\"@en)");
        }
        String query = String.format(queryFinder.find("getArtistsHometownsQuery"), tmp);
        TupleQueryResult result = sparql.select(query);
        List<ArtistEntity> artists = new ArrayList<>();
        //TODO: Fix empty name
        while (result.hasNext()) {
            BindingSet bs = result.next();
            ArtistEntity artist = new ArtistEntity("", bs);
            artists.add(artist);
        }
        return artists;
    }
}
