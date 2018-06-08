package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.spring.sparql.entities.dataEntities.ArtistEntity;
import org.spring.sparql.external.DBPediaController;
import org.spring.sparql.external.LastFmController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AppController {
    @Autowired
    LastFmController lastFmController;
    @Autowired
    DBPediaController dbPediaController;
    public Collection<Artist> getTopArtists(){
        return lastFmController.getTopArtists();
    }

    public Collection<Artist> getTopArtists(int limit){
        return lastFmController.getTopArtists(limit);
    }

    public Collection<ArtistEntity> getArtistsHometowns() throws Exception{
        Collection<ArtistEntity> results = new ArrayList<>();
        Collection<Artist> topArtists = lastFmController.getTopArtists();
        for (Artist artist: topArtists){
            Collection<ArtistEntity> artists = dbPediaController.getArtistHometown(artist.getName());
            results.addAll(artists);
        }
        return results;
    }
}
