package com.repository.implementation;

import com.repository.core.Album;
import com.repository.core.Artist;
import java.util.concurrent.ConcurrentHashMap;

public class AlbumArtistActions {
  protected static ConcurrentHashMap<String, Artist> artistRepository = new ConcurrentHashMap<>();
  protected static ConcurrentHashMap<String, Album> albumRepository = new ConcurrentHashMap<>();
}
