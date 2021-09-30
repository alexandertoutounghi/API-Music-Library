/* [PACKAGE] */
package com.repository.core;

/* [IMPORTS] */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/* [CLASS] */
public class Album implements Serializable {
  // [INSTANCE VARS]
  private String isrc;
  private String title;
  private String content_description;
  private short release_year;
  private Artist artist;

  /* [CONSTRUCTORS] */
  public Album() {}

  public Album(Album otherAlbum) {
    this.isrc = otherAlbum.getIsrc();
    this.title = otherAlbum.getTitle();
    this.content_description = otherAlbum.getContentDescription();
    this.release_year = otherAlbum.getRelease_year();
    if (otherAlbum.getArtist() != null) this.artist = new Artist(otherAlbum.getArtist());
  }

  @JsonIgnoreProperties
  public Album(
    @JsonProperty String isrc,
    @JsonProperty String title,
    @JsonProperty String content_description,
    @JsonProperty short release_year,
    @JsonProperty Artist artist
  ) {
    this.isrc = isrc;
    this.title = title;
    this.content_description = content_description;
    this.release_year = release_year;
    this.artist = artist;
  }

  /* [GETTERS] */
  public String getIsrc() {
    return isrc;
  }

  public String getTitle() {
    return title;
  }

  public String getContentDescription() {
    return content_description;
  }

  public short getRelease_year() {
    return release_year;
  }

  public Artist getArtist() {
    return artist;
  }

  /* [SETTERS] */
  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  // [TOSTRING]

  @Override
  public String toString() {
    return (
      "Album{" +
      "isrc='" +
      isrc +
      '\'' +
      ", title='" +
      title +
      '\'' +
      ", content_description='" +
      content_description +
      '\'' +
      ", release_year=" +
      release_year +
      ", artist=" +
      artist +
      '}'
    );
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (obj instanceof Album otherAlbum) {
          return otherAlbum.isrc.equals(this.isrc);
      } else return false;
  }
}
