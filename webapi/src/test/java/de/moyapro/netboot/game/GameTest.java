package de.moyapro.netboot.game;

import static org.junit.Assert.assertEquals;

import de.moyapro.netboot.entities.Pos;
import de.moyapro.netboot.graphics.BoardRenderer;
import de.moyapro.netboot.storeage.EntityStore;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the game on topmost level
 */
public class GameTest {

  private Game game;

  @Before
  public void setup() {
    game = new Game(
      new EntityStore(),
      new MoveEngine(),
      new BoardRenderer()
    );
  }

  @Test
  public void _3x3() {
    game.newGame(3, 3);
    assertEquals("Game should graphics correct", "...\n...\n...", game.render());
  }

  @Test
  public void _2x2() {
    game.newGame(2, 2);
    assertEquals("Game should graphics correct", "..\n..", game.render());
  }

  @Test
  public void _3x4() {
    game.newGame(3, 4);
    assertEquals("Game should graphics correct", "...\n...\n...\n...", game.render());
  }

  @Test
  public void _1x1withPlayer() {
    assertEquals("Game should graphics correct", "@",
      game.newGame(1, 1)
        .addPlayer(new Pos(0, 0))
        .render());
  }

  @Test
  public void _3x3withPlayer() {
    assertEquals("Game should graphics correct", "...\n.@.\n...",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .render());
  }

  @Test
  public void _3x3withPlayerMoveRight() {
    assertEquals("Game should graphics correct after handleAction", "...\n..@\n...",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .handleAction('l')
        .render());
  }

  @Test
  public void _3x3withPlayerMoveLeft() {
    assertEquals("Game should graphics correct after handleAction", "...\n@..\n...",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .handleAction('h')
        .render());
  }

  @Test
  public void _3x3withPlayerMoveUp() {
    assertEquals("Game should graphics correct after handleAction", ".@.\n...\n...",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .handleAction('k')
        .render());
  }

  @Test
  public void _3x3withPlayerMoveDown() {
    assertEquals("Game should graphics correct after handleAction", "...\n...\n.@.",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .handleAction('j')
        .render());
  }

  @Test
  public void _1x1withPlayerMoveAllDirections() {
    assertEquals("Game should graphics correct after handleAction", "@",
      game.newGame(1, 1)
        .addPlayer(new Pos(0, 0))
        .handleAction('h')
        .handleAction('j')
        .handleAction('k')
        .handleAction('l')
        .render());
  }

  @Test
  public void _2x1withWall() {
    assertEquals("Game should graphics correct with walls", "▓▓",
      game.newGame(2, 1)
        .addWall(new Pos(0, 0))
        .addWall(new Pos(1, 0))
        .render());
  }

  @Test
  public void _3x3withWallFloorPlayer() {
    assertEquals("Game should graphics correct", ".▓.\n▓.▓\n▓@.",
      game.newGame(3, 3)
        .addWall(new Pos(1, 0))
        .addWall(new Pos(0, 1))
        .addWall(new Pos(2, 1))
        .addWall(new Pos(0, 2))
        .addPlayer(new Pos(1, 2))
        .render());
  }

  @Test
  public void _3x3WallBlockMovement() {
    assertEquals("Game should not allow movement through walls", "▓▓▓\n▓@▓\n▓▓▓",
      game.newGame(3, 3)
        .addPlayer(new Pos(1, 1))
        .addWall(new Pos(0, 0))
        .addWall(new Pos(0, 1))
        .addWall(new Pos(0, 2))
        .addWall(new Pos(1, 0))
        .addWall(new Pos(1, 2))
        .addWall(new Pos(2, 0))
        .addWall(new Pos(2, 1))
        .addWall(new Pos(2, 2))
        .handleAction('h')
        .handleAction('j')
        .handleAction('k')
        .handleAction('l')
        .render());
  }

  @Test
  public void _2x1withPlayerMonster() {
    assertEquals("Game should graphics correct with monster", "@M",
      game.newGame(2, 1)
        .addPlayer(new Pos(0, 0))
        .addMonster(new Pos(1, 0))
        .render());
  }

  @Test
  public void _2x1withPlayerMonsterAttackOneHit() {
    assertEquals("Game should graphics correct", "..@",
      game.newGame(3, 1)
        .addPlayer(new Pos(0, 0))
        .addMonster(new Pos(1, 0))
        .handleAction('l')
        .handleAction('l')
        .render());
  }

  @Test
  public void loadMapBasic() {
    String mapToLoad = ".";
    assertEquals("Game should be able to load map", mapToLoad, game.loadMap(mapToLoad).render());
  }

  @Test
  public void loadMap() {
    String mapToLoad = ""
      + "..▓..\n"
      + "▓M.M▓\n"
      + "▓▓@▓▓";
    String expectedResultMap = ""
      + "..▓..\n"
      + "▓M@M▓\n"
      + "▓▓.▓▓";

    assertEquals("Game should be able to load map", expectedResultMap, game.loadMap(mapToLoad).handleAction('k').render());
  }

  @Test
  public void invalidActionShouldBeIgnored() {
    String mapToLoad = ""
      + "..▓..\n"
      + "▓M.M▓\n"
      + "▓▓@▓▓";
    assertEquals("Should ignore inputs and not change the game state"
      , mapToLoad
      , game.loadMap(mapToLoad)
        .handleAction('x')
        .handleAction('u')
        .handleAction('\t')
        .handleAction('\n')
        .render()
    );
  }
}
