package org.spring.sparql.service.LastFM;

import de.umass.lastfm.*;

import java.util.Collection;

public class ExtendedLastFmUser {
    public static Collection<Artist> getTopArtists(String user, Period period, int limit, String apiKey) {
        Result result = Caller.getInstance().call("user.getTopArtists", apiKey, new String[]{"user", user, "period", period.getString(), "limit", String.valueOf(limit)});
        return ResponseBuilder.buildCollection(result, Artist.class);
    }
}
