package com.repository.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.repository.core.Album;
import com.repository.core.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/home")
@RestController
public class RestAPI {
  /* [INSTANCE VARS] */
  private final Router endService;

  /* [CONSTRUCTOR] */
  @Autowired
  public RestAPI(Router endService) {
    this.endService = endService;
  }

  /* [METHODS] */
  // Artist methods -> list, getDetails, add, edit, remove
  @GetMapping("/Artist")
  public String listArtists() {
    return endService.listArtist();
  }

  @GetMapping("/Artist/{nickname}")
  public String getArtistDetails(@PathVariable("nickname") String nickname) {
    return endService.getArtistDetails(nickname);
  }

  @PostMapping("/Artist")
  public String addArtist(@RequestBody Artist artist) {
    return endService.addArtist(artist);
  }

  @PutMapping("/Artist")
  public String editArtist(@RequestBody Artist artist) {
    return endService.editArtist(artist);
  }

  @DeleteMapping("/Artist/{nickname}")
  public String deleteArtist(@PathVariable("nickname") String nickname) {
    return endService.removeArtist(nickname);
  }

  // Album methods -> list, getDetails, add, edit, remove
  @GetMapping("/Album")
  public String listAlbums() {
    return endService.listAlbums();
  }

  @GetMapping("/Album/{isrc}")
  public String getAlbumDetails(@PathVariable("isrc") String isrc) {
    return endService.getAlbumDetails(isrc);
  }

  @PostMapping("/AlbumJson")
  public String createAlbum(@RequestBody ObjectNode json) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Album album = mapper.convertValue(json.get("album"), Album.class);
    album.setArtist(new Artist(json.get("nickname").asText()));
    return endService.addAlbum(album);
  }

  @PostMapping("/Album")
  public String createNewAlbum(@RequestBody Album album) {
    try {
      return endService.addAlbum(new Album(album));
    } catch (Exception e) {
      return "ERROR: "+ e.getMessage();
    }
  }

  @PutMapping("/AlbumJson")
  public String editAlbum(@RequestBody ObjectNode json) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Album album = mapper.convertValue(json.get("album"), Album.class);
    album.setArtist(new Artist(json.get("nickname").asText()));
    return endService.editAlbum(album);
  }

  @PutMapping("/Album")
  public String editOldAlbum(@RequestBody Album album) {
    return endService.editAlbum(album);
  }

  @DeleteMapping("/Album/{isrc}")
  public String deleteAlbum(@PathVariable("isrc") String isrc) {
    return endService.removeAlbum(isrc);
  }

  // Generic error handling
  @ExceptionHandler(Exception.class)
  public String handleError() {
    return "ERROR";
  }
}
