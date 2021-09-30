package com.repository.service;

import com.repository.core.Album;
import com.repository.core.Artist;
import com.repository.implementation.IAlbumActions;
import com.repository.implementation.IArtistActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Router {
  /* [INSTANCE VARS] */
  private final IAlbumActions albumActions;
  private final IArtistActions artistActions;

  /* [CONSTRUCTOR] */
  @Autowired
  public Router(
    @Qualifier("Album") IAlbumActions albumActions,
    @Qualifier("Artist") IArtistActions artistActions
  ) {
    this.albumActions = albumActions;
    this.artistActions = artistActions;
  }

  /* [METHODS] */
  // Artist methods -> list, getDetails, add, edit, remove
  public String listArtist() {
    return artistActions.listArtist();
  }

  public String getArtistDetails(String nickname) {
    return artistActions.getDetails(nickname);
  }

  public String addArtist(Artist artist) {
    return artistActions.add(artist);
  }

  public String editArtist(Artist artist) {
    return artistActions.update(artist);
  }

  public String removeArtist(String nickname) {
    return artistActions.delete(nickname);
  }

  // Album methods -> list, getDetails, add, edit, remove
  public String listAlbums() {
    return albumActions.list();
  }

  public String getAlbumDetails(String isrc) {
    return albumActions.getDetails(isrc);
  }

  public String addAlbum(Album album) {
    return albumActions.add(album);
  }

  public String editAlbum(Album album) {
    return albumActions.update(album);
  }

  public String removeAlbum(String isrc) {
    return albumActions.delete(isrc);
  }
}
