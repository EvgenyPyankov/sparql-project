package org.spring.sparql.service.LastFM;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.Period;
import de.umass.lastfm.User;
import org.spring.sparql.constants.Credentials;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class LastFmController {
    private String key;
    private final int DEFALUT_LIMIT = 20;

    public LastFmController(String key) {
        Caller.getInstance().setUserAgent("tst");
        this.key = key;
    }

    public LastFmController() {
        this.key = Credentials.LAST_FM_KEY;
    }

    public Collection<Artist> getTopArtists(String user) {
        Collection<Artist> artists = getTopArtists(user, DEFALUT_LIMIT);
        return artists;
    }

    public Collection<Artist> getTopArtists(String user, int limit) {
        Collection<Artist> artists = ExtendedLastFmUser.getTopArtists(user, Period.OVERALL, limit, key);
        return artists;
    }

    public boolean userExists(String user){
      User u = User.getInfo(user, key);
        return u != null;
    }
}
