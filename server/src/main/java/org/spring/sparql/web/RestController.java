package org.spring.sparql.web;

import de.umass.lastfm.Artist;
import org.spring.sparql.controllers.AppController;
import org.spring.sparql.entity.ArtistEntity;
import org.spring.sparql.service.LastFM.LastFmController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "http://localhost:8082")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    AppController controller;

    @Autowired
    LastFmController lastFmController;

    @RequestMapping(value = "/artists", method = GET)
    public ResponseEntity<Object> getArtistsByUser(@RequestParam("user") String user,
                                          @RequestParam("limit") int limit) {
        Collection<Artist> artists = controller.getTopArtists(user, limit);
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping("/hometowns")
    public ResponseEntity<Object> getArtistsHometownsByUser(@RequestParam("user") String user,
                                                            @RequestParam("limit") int limit) throws Exception {
        try {
            Collection<ArtistEntity> artists = controller.getArtistsHometowns(user, limit);
            return new ResponseEntity<Object>(artists, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

