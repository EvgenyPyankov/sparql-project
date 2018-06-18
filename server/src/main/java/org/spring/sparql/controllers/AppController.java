package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.spring.sparql.entity.ArtistEntity;
import org.spring.sparql.service.DBPedia.DBPediaController;
import org.spring.sparql.service.LastFM.LastFmController;
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
    public Collection<Artist> getTopArtists(String user){
        return lastFmController.getTopArtists(user);
    }

    public Collection<Artist> getTopArtists(String user, int limit){
        return lastFmController.getTopArtists(user, limit);
    }

    public Collection<ArtistEntity> getArtistsHometowns(String user, int limit) throws Exception{
        if (!lastFmController.userExists(user)){
            throw new RuntimeException("No such user");
        }
        Collection<ArtistEntity> results = new ArrayList<>();
        Collection<Artist> topArtists = lastFmController.getTopArtists(user, limit);
        for (Artist artist: topArtists){
            Collection<ArtistEntity> artists = dbPediaController.getArtistHometown(artist.getName());
            results.addAll(artists);
        }
        return results;
    }
}
