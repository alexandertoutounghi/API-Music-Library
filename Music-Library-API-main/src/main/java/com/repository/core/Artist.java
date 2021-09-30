/* [PACKAGE] */
package com.repository.core;

/* [IMPORTS] */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.io.Serializable;

/* [CLASS] */
public class Artist implements Serializable {
  // [INSTANCE VARS]
  private String nickname;
  private String first_name;
  private String last_name;
  private String bio;

  // [CONSTRUCTORS]
  public Artist() {}

  public Artist(Artist otherArtist) {
    this.nickname = otherArtist.getNickname();
    this.first_name = otherArtist.getFirst_name();
    this.last_name = otherArtist.getLast_name();
    this.bio = otherArtist.getBio();
  }

  public Artist(String nickname) {
    this.nickname = nickname;
  }

  public Artist(
    @JsonProperty @NotNull String nickname,
    @JsonProperty @NotNull String first_name,
    @JsonProperty @NotNull String last_name,
    @JsonProperty @NotNull String bio
  ) {
    this.nickname = nickname;
    this.first_name = first_name;
    this.last_name = last_name;
    this.bio = bio;
  }

  // [GETTERS]
  public String getNickname() {
    return nickname;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getBio() {
    return bio;
  }

  // [TOSTRING]
  @Override
  public String toString() {
    return (
      "Artist{" +
      "nickname='" +
      nickname +
      '\'' +
      ", first_name='" +
      first_name +
      '\'' +
      ", last_name='" +
      last_name +
      '\'' +
      ", bio='" +
      bio +
      '\'' +
      '}'
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (obj instanceof Artist otherArtist) {
      return otherArtist.nickname.equals(this.nickname);
    } else return false;
  }
}
