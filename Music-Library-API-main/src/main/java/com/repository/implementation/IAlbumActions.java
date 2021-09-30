package com.repository.implementation;

import com.repository.core.Album;

public interface IAlbumActions {
  /**
   * Returns the list of all albums by keypair <[ISRC], [title]>
   * @return ArrayList of Pair representing albums by isrc and title
   */
  String list();

  /**
   * Returns details for a specific album
   * @param isrc isrc of the album
   * @return Details of a specific album
   */
  String getDetails(String isrc);

  /**
   * Adds an album
   * @param newAlbum new album information
   * @return Validation or error message
   */
  String add(Album newAlbum);

  /**
   * Updates a specific album
   * @param newAlbum new album information
   * @return Validation or error message
   */
  String update(Album newAlbum);

  /**
   * Deletes a specific album
   * @param isrc isrc (unique)
   * @return Validation or error message
   */
  String delete(String isrc);
}
