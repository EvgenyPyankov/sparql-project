package org.spring.sparql.entities.dataEntities;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class ArtistEntity {
    private String band;
    private String hometown;
    private Location location;

    public ArtistEntity(String band, String hometown) {
        this.band = band;
        this.hometown = hometown;
    }

    public ArtistEntity(BindingSet bs){
        Value band = bs.getValue("band");
        Value hometown = bs.getValue("hometown");
        Value lat = bs.getValue("lat");
        Value lon = bs.getValue("lon");
        Double doubleLat = lat != null? Double.parseDouble(lat.stringValue()): 0;
        Double doubleLon = lon != null? Double.parseDouble(lon.stringValue()): 0;
        this.band = band.stringValue();
        this.hometown = hometown.stringValue();
        this.location = new Location(doubleLat, doubleLon);
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
