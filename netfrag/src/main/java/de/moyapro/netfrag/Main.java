package de.moyapro.netfrag;

import de.moyapro.netfrag.entities.Pos;
import de.moyapro.netfrag.game.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * start your adventure here
 */
public class Main {

  private static PrintStream outputTarget = System.out;
  private static InputStream inputSource = System.in;


  public static void main(String[] args) {
    Game game = Game
      .getInstance(10, 10)
      .addWall(new Pos(1, 2))
      .addWall(new Pos(2, 2))
      .addWall(new Pos(3, 2))
      .addWall(new Pos(4, 2))
      .addWall(new Pos(5, 2))
      .addPlayer(new Pos(1, 1))
      .addMonster(new Pos(2, 1));
    outputTarget.print(game.render());
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputSource));
    while (true) {

      try {
        String input = reader.readLine();
        outputTarget.println(String.format("Input was: %s", input));
        char lastAction = input.charAt(0);
        if ('q' == lastAction) {
          outputTarget.println("Bye!");
          System.exit(0);
        }
        game.handleAction(lastAction);
        outputTarget.print(game.render());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
