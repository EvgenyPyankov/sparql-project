package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.spring.sparql.external.LastFmController;

import java.util.Collection;

public class AppController {
    public Collection<Artist> getTopArtists(){
        LastFmController lastFmController = new LastFmController();
        return lastFmController.getTopArtists();
    }
}
