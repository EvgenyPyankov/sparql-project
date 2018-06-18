package org.spring.sparql.service.LastFM;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Period;
import de.umass.lastfm.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class LastFmController {

    @Value("${app.lastfm.key}")
    private String key;
    private final int DEFALUT_LIMIT = 20;

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
