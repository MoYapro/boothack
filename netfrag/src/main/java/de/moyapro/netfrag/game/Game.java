package de.moyapro.netfrag.game;

import de.moyapro.netfrag.api.MoveAction;
import de.moyapro.netfrag.entities.Monster;
import de.moyapro.netfrag.entities.Player;
import de.moyapro.netfrag.entities.Pos;
import de.moyapro.netfrag.entities.Wall;
import de.moyapro.netfrag.graphics.BoardRenderer;
import de.moyapro.netfrag.storeage.EntityStore;

/**
 * Main game logic (for now)
 */
public class Game {

  private final EntityStore entityStore;
  private final MoveEngine moveEngine;
  private final BoardRenderer renderer;

  private Game(int width, int height) {
    moveEngine = new MoveEngine();
    renderer = new BoardRenderer();
    entityStore = new EntityStore(width, height);
  }

  private Game(String mapToLoad) {
    moveEngine = new MoveEngine();
    renderer = new BoardRenderer();
    entityStore = new EntityStore(mapToLoad);
  }

  public static Game getInstance(int width, int height) {
    return new Game(width, height);
  }

  public static Game getInstance(String mapToLoad) {
    return new Game(mapToLoad);
  }

  public String render() {
    return renderer.render(entityStore);
  }

  @SuppressWarnings("WeakerAccess")

  public Game handleAction(char action) {
    if ('h' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.LEFT, entityStore); }
    if ('j' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.DOWN, entityStore); }
    if ('k' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.UP, entityStore); }
    if ('l' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.RIGHT, entityStore); }
    return this;
  }

  public Game addPlayer(Pos pos) {
    entityStore.setObjectAtPosition(new Player(), pos);
    return this;
  }

  public Game addWall(Pos pos) {
    this.entityStore.setObjectAtPosition(new Wall(), pos);
    return this;
  }

  public Game addMonster(Pos pos) {
    this.entityStore.setObjectAtPosition(new Monster(), pos);
    return this;
  }
}
