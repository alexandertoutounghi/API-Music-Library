package com.repository.CLI;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.tools.ant.types.Commandline;
import picocli.CommandLine;

public class Run {

  public static void main(String[] args) {
    System.out.println("Welcome to the Artist/Album Repository!");
    Scanner in = new Scanner(System.in);
    CommandLine commandLine = new CommandLine(new Commands());
    commandLine.usage(System.err);

    ArrayList<String[]> exampleCommands = new ArrayList<>();

    exampleCommands.add(
      new String[] {
        "ARTCMD",
        "ADD",
        "-n",
        "eminem",
        "-f",
        "marshall",
        "-l",
        "mathers",
        "-b",
        "rich"
      }
    );
    exampleCommands.add(
      new String[] {
        "ALBCMD",
        "ADD",
        "-t",
        "Recovery",
        "-i",
        "432-132-232",
        "-c",
        "asdfasdf",
        "-r",
        "2020",
        "-n",
        "eminem"
      }
    );
    exampleCommands.add(new String[] { "ARTCMD", "RMV", "-n", "jk rowling" });
    exampleCommands.add(new String[] { "ARTCMD", "LST", "-s", "jk rowling" });
    exampleCommands.add(new String[] { "ARTCMD", "LST", "-a" });

    System.out.println("Example commands: ");
    exampleCommands.forEach(cmd -> System.out.println(String.join(" ", cmd)));

    while (true) {
      try {
        System.out.println("Enter a command...");
        String cmd = in.nextLine();
        args = Commandline.translateCommandline(cmd);
        int exitCode = new CommandLine(new Commands()).execute(args);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
