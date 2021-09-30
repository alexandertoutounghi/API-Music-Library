package com.repository.implementation;

import com.repository.core.Album;
import com.repository.core.Artist;
import org.springframework.stereotype.Repository;

@Repository("Album")
public class AlbumActions extends AlbumArtistActions implements IAlbumActions {

  // Only for this assignment, next one will use database

  @Override
  public String list() {
    if (albumRepository.size() == 0) {
      return "No album in the repository...";
    } else {
      StringBuilder res = new StringBuilder();
      albumRepository.forEach(
        (isrc, album) -> res.append(String.format("%s - %s\n", isrc, album.getTitle()))
      );
      return res.toString();
    }
  }

  @Override
  public String getDetails(String isrc) {
    Album album = albumRepository.get(isrc);
    return album == null
      ? String.format("No album with isrc \"%s\" found.", isrc)
      : album.toString();
  }

  @Override
  public String add(Album album) {
    try {
      Artist artist = artistRepository.get(album.getArtist().getNickname());
      if (artist == null) return String.format(
        "Cannot Add: Artist \"%s\" does not exist in repository",
        album.getArtist().getNickname()
      );
      if (albumRepository.containsKey(album.getIsrc())) return String.format(
        "Cannot Add: Album \"%s\" already exists.",
        album.getTitle()
      ); else {
        albumRepository.put(album.getIsrc(), new Album(album));
        return String.format("Success: Album \"%s\" added.", album.getTitle());
      }
    } catch (Exception e) {
      return e.toString();
    }
  }

  public String update(Album newAlbum) {
    Artist artist = artistRepository.get(newAlbum.getArtist().getNickname());
    Album album = albumRepository.get(newAlbum.getIsrc());
    if (artist == null) return String.format(
      "Cannot Update: Artist \"%s\" does not exist in repository",
      newAlbum.getArtist().getNickname()
    );
    if (album == null) return String.format(
      "Cannot Update: Album \"%s\"  does not exist.",
      newAlbum.getTitle()
    );
    Album addAlbum = new Album(newAlbum);
    addAlbum.setArtist(artist);
    albumRepository.put(newAlbum.getIsrc(), newAlbum);
    return String.format("Album \"%s\" updated.", newAlbum.getIsrc());
  }

  public String delete(String isrc) {
    if (!albumRepository.containsKey(isrc)) return String.format(
      "No album with isrc \"%s\" found.",
      isrc
    ); else {
      albumRepository.remove(isrc);
      return String.format("Album \"%s\" removed.", isrc);
    }
  }
}
