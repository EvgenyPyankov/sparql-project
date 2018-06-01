package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.spring.sparql.entities.dataEntities.ArtistEntity;
import org.spring.sparql.external.DBPediaController;
import org.spring.sparql.external.LastFmController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "http://localhost:8082")
@Controller
public class RestController {
    AppController controller = new AppController();

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Evgeny");
        new LastFmController().getTopArtists();
        return "index";
    }

    @RequestMapping("/artists")
    public ResponseEntity<Object> artists(Model model){
        Collection<Artist> artists = controller.getTopArtists();
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "/artists/{limit}", method = GET)
    public ResponseEntity<Object> artists(@PathVariable("limit") int limit){
        System.out.println("here");
        Collection<Artist> artists = controller.getTopArtists(limit);
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "/artist/{name}", method = GET)
    public ResponseEntity<Object> test(@PathVariable("name") String name) throws Exception{
        DBPediaController cntr = new DBPediaController();
        List<ArtistEntity> artists = cntr.getArtistHometown(name);
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }

    @RequestMapping("/hometowns")
    public ResponseEntity<Object> getArtistsHometowns() throws Exception{
        Collection<ArtistEntity> artists = controller.getArtistsHometowns();
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }
}
