package org.spring.sparql.controllers;

import de.umass.lastfm.Artist;
import org.json.simple.JSONObject;
import org.spring.sparql.external.LastFmController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class RestController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Evgeny");
        new LastFmController().getTopArtists();
        return "index";
    }

    @RequestMapping("/artists")
    public ResponseEntity<Object> artists(Model model){
        AppController controller = new AppController();
        Collection<Artist> artists = controller.getTopArtists();
        return new ResponseEntity<Object>(artists, HttpStatus.OK);
    }
}
