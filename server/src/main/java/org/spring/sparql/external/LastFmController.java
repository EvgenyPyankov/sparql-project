package org.spring.sparql.external;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.Period;
import org.spring.sparql.constants.Credentials;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class LastFmController {
    private String user;
    private String key;
    private final int DEFALUT_LIMIT = 100;

    public LastFmController(String user, String key) {
        Caller.getInstance().setUserAgent("tst");
        this.user = user;
        this.key = key;
    }

    public LastFmController() {
        this.user = Credentials.LAST_FM_USER;
        this.key = Credentials.LAST_FM_KEY;
    }

    public Collection<Artist> getTopArtists() {
        Collection<Artist> artists = ExtendedLastFmUser.getTopArtists(user, Period.OVERALL, DEFALUT_LIMIT, key);
        return artists;
    }

    public Collection<Artist> getTopArtists(int limit) {
        Collection<Artist> artists = ExtendedLastFmUser.getTopArtists(user, Period.OVERALL, limit, key);
        return artists;
    }
}
