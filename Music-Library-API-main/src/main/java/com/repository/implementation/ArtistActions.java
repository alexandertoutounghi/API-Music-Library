package com.repository.implementation;

import com.repository.core.Artist;
import org.springframework.stereotype.Repository;

@Repository("Artist")
public class ArtistActions extends AlbumArtistActions implements IArtistActions {

  @Override
  public String listArtist() {
    if (artistRepository.size() == (0)) {
      return "No artists in the repository...";
    } else {
      StringBuilder res = new StringBuilder();

      artistRepository.forEach(
        (nickname, artist) ->
          res.append(
            String.format(
              "%s - %s %s\n",
              nickname,
              artist.getFirst_name(),
              artist.getLast_name()
            )
          )
      );
      return res.toString();
    }
  }

  public String getDetails(String nickname) {
    System.out.println(nickname);
    Artist artist = artistRepository.get(nickname);
    return artist == null
      ? String.format("No artist with nickname \"%s\" found.", nickname)
      : artist.toString();
  }

  @Override
  public String add(Artist newArtist) {
    if (artistRepository.containsKey(newArtist.getNickname())) return String.format(
      "Artist \"%s\" already exists.",
      newArtist.getNickname()
    ); else {
      artistRepository.put(newArtist.getNickname(), new Artist(newArtist));
      return String.format("Artist \"%s\" added.", newArtist.getNickname());
    }
  }

  @Override
  public String update(Artist newArtist) {
    Artist artist = artistRepository.get(newArtist.getNickname());
    if (artist == null) return String.format(
      "No artist with nickname \"%s\" found.",
      newArtist.getNickname()
    );

    Artist replacementArtist = new Artist(newArtist);
    albumRepository.replaceAll(
      (k, v) -> {
        if (v.getArtist().equals(newArtist)) v.setArtist(replacementArtist);
        return v;
      }
    );
    artistRepository.put(newArtist.getNickname(), replacementArtist);
    return String.format("Artist \"%s\" Updated.", newArtist.getNickname());
  }

  @Override
  public String delete(String nickname) {
    System.out.println(nickname);
    if (artistRepository.get(nickname) == null) return String.format(
      "No artist with nickname \"%s\" found.",
      nickname
    ); else {
      artistRepository.remove(nickname);
      return String.format("Artist \"%s\" removed.", nickname);
    }
  }
}
