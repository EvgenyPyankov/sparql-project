package org.spring.sparql.external;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.Period;
import de.umass.lastfm.User;

import java.util.Collection;


public class LastFmController {
    private String user;
    private String key;

    public LastFmController(String user, String key){
        Caller.getInstance().setUserAgent("tst");
        this.user = user;
        this.key = key;
    }

    public LastFmController(){
        this.user = "user";
        this.key = "key";
    }
    public Collection<Artist> getTopArtists(){
        Collection<Artist> artists = User.getTopArtists(user, Period.OVERALL, key);
        return artists;
    }
}
