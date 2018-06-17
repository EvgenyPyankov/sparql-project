package org.spring.sparql.service.DBPedia;

import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;
import org.spring.sparql.entity.ArtistEntity;
import org.spring.sparql.service.SPARQLService;
import org.spring.sparql.utils.QueryFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBPediaController {
    private String URL = "http://dbpedia.org/sparql";

    private SPARQLService sparql;

    @Autowired
    private QueryFinder queryFinder;

    public DBPediaController() {
        sparql = new SPARQLService(URL);
    }

    public List<ArtistEntity> getInfoAboutBand(String name) throws Exception {
        TupleQueryResult result = sparql.select(String.format(queryFinder.find("getInfoAboutArtist"), name));
        List<ArtistEntity> artists = new ArrayList<>();
        while (result.hasNext()) {
            BindingSet bs = result.next();
            ArtistEntity artist = new ArtistEntity(name, bs);
            artists.add(artist);
        }
        return artists;
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
}
