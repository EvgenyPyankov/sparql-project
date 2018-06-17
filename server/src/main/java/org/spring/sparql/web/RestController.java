package org.spring.sparql.web;

import de.umass.lastfm.Artist;
import org.spring.sparql.controllers.AppController;
import org.spring.sparql.entity.ArtistEntity;
import org.spring.sparql.service.DBPedia.DBPediaController;
import org.spring.sparql.service.LastFM.LastFmController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "http://localhost:8082")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    AppController controller;

    @Autowired
    LastFmController lastFmController;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Evgeny");
        return "index";
    }

    @RequestMapping("/artists")
    public ResponseEntity<Object> artists(Model model) {
        Collection<Artist> artists = controller.getTopArtists();
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "/artists/{limit}", method = GET)
    public ResponseEntity<Object> artists(@PathVariable("limit") int limit) {
        System.out.println("here");
        Collection<Artist> artists = controller.getTopArtists(limit);
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "/artist/{name}", method = GET)
    public ResponseEntity<Object> test(@PathVariable("name") String name) throws Exception {
        DBPediaController cntr = new DBPediaController();
        List<ArtistEntity> artists = cntr.getArtistHometown(name);
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
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

