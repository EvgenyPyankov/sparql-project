package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.spring.sparql.external.LastFmController;

import java.util.Collection;

public class AppController {
    LastFmController lastFmController = new LastFmController();
    public Collection<Artist> getTopArtists(){
        return lastFmController.getTopArtists();
    }

    public Collection<Artist> getTopArtists(int limit){
        return lastFmController.getTopArtists(limit);
    }
}
