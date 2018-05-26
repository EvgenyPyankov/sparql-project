package org.spring.sparql.external;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;
import org.spring.sparql.constants.Queries;
import org.spring.sparql.entities.dataEntities.ArtistEntity;
import org.spring.sparql.services.SPARQLService;

import java.util.ArrayList;
import java.util.List;

public class DBPediaController {
    private String URL = "http://dbpedia.org/sparql";
    SPARQLService sparql;

    public DBPediaController() {
        sparql = new SPARQLService(URL);
    }

    public void getInfoAboutBand() throws Exception{
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

    public List<ArtistEntity> getArtistHometown(String name) throws Exception{
        String query = String.format(Queries.GET_ARTIST_HOMETOWN_LOCATION_QUERY, name);
        TupleQueryResult result = sparql.select(query);
        List<ArtistEntity> artists = new ArrayList<>();
        while (result.hasNext()) {
            BindingSet bs = result.next();
            ArtistEntity artist = new ArtistEntity(bs);
            artists.add(artist);
        }
        return artists;
    }

    public List<ArtistEntity> getArtistsHometowns(String[]names) throws Exception{
        String tmp = "";
        for (String name: names){
            tmp+=String.format("(\"%s\"@en)");
        }
        String query = String.format(Queries.GET_ARTISTS_HOMETOWNS_QUERY, tmp);
        TupleQueryResult result = sparql.select(query);
        List<ArtistEntity> artists = new ArrayList<>();
        while (result.hasNext()) {
            BindingSet bs = result.next();
            ArtistEntity artist = new ArtistEntity(bs);
            artists.add(artist);
        }
        return artists;
    }
}
