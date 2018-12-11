package de.moyapro.netfrag;

import java.io.IOException;
import jline.TerminalSupport;
import jline.console.ConsoleReader;
public class Main2 {

  private static ConsoleReader consoleReader = null;

  public static void main(String[] args) {
    try {
      consoleReader = new ConsoleReader(System.in, System.out, new TerminalSupport(false) {});
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      System.out.println(consoleReader.readCharacter());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
