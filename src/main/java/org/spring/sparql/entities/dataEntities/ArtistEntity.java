package org.spring.sparql.entities.dataEntities;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class ArtistEntity {
    private String band;
    private String bandName;
    private String hometown;
    private String hometownName;
    private Location location;

    public ArtistEntity(String band, String hometown) {
        this.band = band;
        this.hometown = hometown;
    }

    public ArtistEntity(String name, BindingSet bs){
        Value band = bs.getValue("band");
        Value hometown = bs.getValue("hometown");
        Value hometownName = bs.getValue("hometownName");
        Value lat = bs.getValue("lat");
        Value lon = bs.getValue("lon");
        Double doubleLat = lat != null? Double.parseDouble(lat.stringValue()): 0;
        Double doubleLon = lon != null? Double.parseDouble(lon.stringValue()): 0;
        this.band = band.stringValue();
        this.bandName = name;
        this.hometown = hometown.stringValue();
        this.hometownName = hometownName.stringValue();
        this.location = new Location(doubleLat, doubleLon);
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHometownName() {
        return hometownName;
    }

    public void setHometownName(String hometownName) {
        this.hometownName = hometownName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
