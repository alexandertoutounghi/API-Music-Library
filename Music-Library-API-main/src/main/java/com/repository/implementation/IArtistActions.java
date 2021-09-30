package com.repository.implementation;

import com.repository.core.Artist;

public interface IArtistActions {
  /**
   * Returns the list of all artists by keypair <[nickname], [first_name + last_name]>
   * @return ArrayList of Pair representing artists by nickname and full name
   */
  String listArtist();

  /**
   * Returns artist details
   * @param nickname nickname of artist
   * @return Details of a specific artist
   */
  String getDetails(String nickname);

  /**
   * Adds an artist
   * @param newArtist new artist information
   * @return Validation or error message
   */
  String add(Artist newArtist);

  /**
   * Updates a specific artist
   * @param newArtist new artist information
   * @return Validation or error message
   */
  String update(Artist newArtist);

  /**
   * Deletes a specific artist
   * @param nickname nickname of artist
   * @return Validation or error message
   */
  String delete(String nickname);
}
