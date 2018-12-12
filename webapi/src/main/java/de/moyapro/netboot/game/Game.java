package de.moyapro.netboot.game;

import de.moyapro.netboot.api.MoveAction;
import de.moyapro.netboot.entities.Monster;
import de.moyapro.netboot.entities.Player;
import de.moyapro.netboot.entities.Pos;
import de.moyapro.netboot.entities.Wall;
import de.moyapro.netboot.graphics.BoardRenderer;
import de.moyapro.netboot.storeage.EntityStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Main game logic (for now)
 */
@Service
public class Game {

  private final EntityStore entityStore;
  private final MoveEngine moveEngine;
  private final BoardRenderer renderer;

  @Autowired
  public Game(EntityStore entityStore, MoveEngine moveEngine, BoardRenderer renderer) {
    this.entityStore = entityStore;
    this.moveEngine = moveEngine;
    this.renderer = renderer;
  }

  public String render() {
    return renderer.render(entityStore);
  }

  public Game handleAction(char action) {
    if ('h' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.LEFT, entityStore); }
    if ('j' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.DOWN, entityStore); }
    if ('k' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.UP, entityStore); }
    if ('l' == action) { moveEngine.move(entityStore.getPlayer(), MoveAction.RIGHT, entityStore); }
    return this;
  }

  Game addPlayer(Pos pos) {
    entityStore.setObjectAtPosition(new Player(), pos);
    return this;
  }

  Game addWall(Pos pos) {
    this.entityStore.setObjectAtPosition(new Wall(), pos);
    return this;
  }

  Game addMonster(Pos pos) {
    this.entityStore.setObjectAtPosition(new Monster(), pos);
    return this;
  }

  public Game loadMap(String map) {
    entityStore.loadMap(map);
    return this;
  }

  Game newGame(int width, int height) {
    entityStore.newGame(width, height);
    return this;
  }
}
