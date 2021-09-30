package com.repository.CLI;

import com.repository.core.Album;
import com.repository.core.Artist;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
  name = "ALBCMD",
  description = "Album related command",
  subcommands = {
    AlbumCommands.ADD.class,
    AlbumCommands.UPD.class,
    AlbumCommands.RMV.class,
    AlbumCommands.LST.class
  }
)
public class AlbumCommands {
  private static APIHandler api = new APIHandler("/Album");

  @CommandLine.Command(name = "ADD", description = "Add a new Album")
  public static class ADD implements Callable<Integer> {
    @CommandLine.Option(
      names = { "-t", "--title" },
      description = { "The albums title" },
      required = true
    )
    String title;

    @CommandLine.Option(
      names = { "-i", "--isrc" },
      description = { "The albums isrc" },
      required = true
    )
    String isrc;

    @CommandLine.Option(
      names = { "-c", "--content-description" },
      description = { "The content description for the book" },
      required = true
    )
    String content_description;

    @CommandLine.Option(
      names = { "-r", "--release-year" },
      description = { "The albums published year" },
      required = true
    )
    short release_year;

    @CommandLine.Option(
      names = { "-n", "--nickname" },
      description = { "The albums artists nickname" },
      required = true
    )
    String nickname;

    @Override
    public Integer call() throws Exception {
      System.out.printf(
        "Title: %s\nNickname:%s\nReleaseYear:%d\nISRC: %s\nContent_Description:%s\n",
        title,
        nickname,
        release_year,
        isrc,
        content_description
      );
      System.out.println(
        api.Put(
          new Album(isrc, title, content_description, release_year, new Artist(nickname))
        )
      );
      return 0;
    }
  }

  @CommandLine.Command(name = "RMV", description = "Remove an existing Album by isrc")
  public static class RMV implements Callable<Integer> {
    @CommandLine.Option(
      names = { "-i", "--isrc" },
      description = { "albums isrc" },
      required = true
    )
    String isrc;

    @Override
    public Integer call() throws Exception {
      System.out.println(api.Delete(isrc));
      return 0;
    }
  }

  @CommandLine.Command(name = "UPD", description = "Update an existing Album details")
  public static class UPD implements Callable<Integer> {
    @CommandLine.Option(
      names = { "-t", "--title" },
      description = { "The albums title" },
      required = true
    )
    String title;

    @CommandLine.Option(
      names = { "-i", "--isrc" },
      description = { "The albums isrc" },
      required = true
    )
    String isrc;

    @CommandLine.Option(
      names = { "-c", "--content-description" },
      description = { "The content description for the album" },
      required = true
    )
    String content_description;

    @CommandLine.Option(
      names = { "-r", "--release-year" },
      description = { "The albums published year" },
      required = true
    )
    short release_year;

    @CommandLine.Option(
      names = { "-n", "--nickname" },
      description = { "The album artists nickname" },
      required = true
    )
    String nickname;

    @Override
    public Integer call() {
      System.out.println(
        new Album(isrc, title, content_description, release_year, new Artist(nickname))
      );
      return 0;
    }
  }

  @CommandLine.Command(
    name = "LST",
    description = "List all the Albums in the Repository"
  )
  public static class LST implements Callable<Integer> {
    @CommandLine.ArgGroup(exclusive = true, multiplicity = "1")
    List list;

    public static class List {
      @CommandLine.Option(
        names = { "-a", "-all" },
        description = "List all the album",
        required = true
      )
      boolean listAll;

      @CommandLine.Option(
        names = { "-s", "--search" },
        description = "List a particular album by isrc",
        required = true
      )
      String isrc;
    }

    @Override
    public Integer call() throws Exception {
      if (list.isrc == null || list.isrc.isEmpty()) System.out.println(
        api.getAll()
      ); else System.out.println(api.getDetails(list.isrc));
      return 0;
    }
  }
}
