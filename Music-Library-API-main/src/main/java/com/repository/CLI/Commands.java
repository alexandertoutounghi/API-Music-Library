package com.repository.CLI;

import com.repository.core.Album;
import com.repository.core.Artist;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
  name = "CMD",
  description = "Command",
  subcommands = {
    Commands.ArtistCommands.class, Commands.AlbumCommands.class, Commands.Terminate.class
  }
)
public class Commands {

  @CommandLine.Command(
    name = "ARTCMD",
    description = "Artist related command",
    subcommands = {
      com.repository.CLI.ArtistCommands.ADD.class,
      com.repository.CLI.ArtistCommands.UPD.class,
      com.repository.CLI.ArtistCommands.RMV.class,
      com.repository.CLI.ArtistCommands.LST.class
    }
  )
  public static class ArtistCommands {
    private static final APIHandler api = new APIHandler("/Artist");

    @CommandLine.Command(name = "ADD", description = "Add a new Artist ")
    public static class ADD implements Callable<Integer> {
      @CommandLine.Option(names = { "-n", "--nickname" }, required = true)
      String nickname;

      @CommandLine.Option(names = { "-f", "--firstname" }, required = true)
      String first_name;

      @CommandLine.Option(names = { "-l", "--lastName" }, required = true)
      String last_name;

      @CommandLine.Option(names = { "-b", "--biography" }, required = true)
      String bio;

      @Override
      public Integer call() throws Exception {
        System.out.println(api.Post(new Artist(nickname, first_name, last_name, bio)));
        return 0;
      }
    }

    @CommandLine.Command(
      name = "RMV",
      description = "Remove an existing Artist by nickname"
    )
    public static class RMV implements Callable<Integer> {
      @CommandLine.Option(names = { "-n", "--nickname" }, required = true)
      String nickname;

      @Override
      public Integer call() throws Exception {
        System.out.println(api.Delete(nickname));
        return 0;
      }
    }

    @CommandLine.Command(name = "UPD", description = "Update an existing Artist details")
    public static class UPD implements Callable<Integer> {
      @CommandLine.Option(names = { "-n", "--nickname" }, required = true)
      String nickname;

      @CommandLine.Option(names = { "-f", "--firstname" }, required = true)
      String first_name;

      @CommandLine.Option(names = { "-l", "--lastName" }, required = true)
      String last_name;

      @CommandLine.Option(names = { "-b", "--biography" }, required = true)
      String bio;

      @Override
      public Integer call() throws Exception {
        System.out.println(api.Put(new Artist(nickname, first_name, last_name, bio)));
        return 0;
      }
    }

    @CommandLine.Command(
      name = "LST",
      description = "List all the artists in the Repository"
    )
    public static class LST implements Callable<Integer> {
      @CommandLine.ArgGroup(exclusive = true, multiplicity = "1")
      com.repository.CLI.ArtistCommands.LST.List list;

      public static class List {
        @CommandLine.Option(
          names = { "-a", "-all" },
          description = "List all the artists",
          required = true
        )
        boolean listAll;

        @CommandLine.Option(
          names = { "-s", "--search" },
          description = "List a particular artist by nickname",
          required = true
        )
        String nickname;
      }

      @Override
      public Integer call() throws Exception {
        if (list.nickname == null || list.nickname.isEmpty()) System.out.println(
          api.getAll()
        ); else System.out.println(api.getDetails(list.nickname));
        return 0;
      }
    }
  }

  @CommandLine.Command(
    name = "ALBCMD",
    description = "Album related command",
    subcommands = {
      com.repository.CLI.AlbumCommands.ADD.class,
      com.repository.CLI.AlbumCommands.UPD.class,
      com.repository.CLI.AlbumCommands.RMV.class,
      com.repository.CLI.AlbumCommands.LST.class
    }
  )
  public static class AlbumCommands {
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
          api.Post(
            new Album(
              isrc,
              title,
              content_description,
              release_year,
              new Artist(nickname)
            )
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
      com.repository.CLI.AlbumCommands.LST.List list;

      public class List {
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
        if (list.isrc.isEmpty()) System.out.println(
          api.getAll()
        ); else System.out.println(api.getDetails(list.isrc));
        return 0;
      }
    }
  }

  @CommandLine.Command(name = "EXIT", description = "Terminate the Script")
  public static class Terminate implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      System.out.println("Terminating Program and Exiting DB!");
      System.exit(0);
      return 0;
    }
  }
}
