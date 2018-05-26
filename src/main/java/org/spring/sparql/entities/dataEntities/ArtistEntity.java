package org.spring.sparql.entities.dataEntities;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class ArtistEntity {
    private String band;
    private String hometown;

    public ArtistEntity(String band, String hometown) {
        this.band = band;
        this.hometown = hometown;
    }

    public ArtistEntity(BindingSet bs){
        Value band = bs.getValue("band");
        Value hometown = bs.getValue("hometown");
        this.band = band.stringValue();
        this.hometown = hometown.stringValue();
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
}
