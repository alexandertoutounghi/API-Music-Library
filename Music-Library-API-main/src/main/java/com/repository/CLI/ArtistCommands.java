package com.repository.CLI;

import com.repository.core.Artist;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
  name = "ARTCMD",
  description = "Artist related command",
  subcommands = {
    ArtistCommands.ADD.class,
    ArtistCommands.UPD.class,
    ArtistCommands.RMV.class,
    ArtistCommands.LST.class
  }
)
public class ArtistCommands {
  private static APIHandler api = new APIHandler("/Artist");

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
    List list;

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
