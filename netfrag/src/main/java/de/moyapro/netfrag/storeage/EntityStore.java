package de.moyapro.netfrag.storeage;

import de.moyapro.netfrag.api.Entity;
import de.moyapro.netfrag.entities.Floor;
import de.moyapro.netfrag.entities.Pos;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

/**
 * Store state of the board and provide access methods to manipulate that state
 */
public class EntityStore {

  @Getter
  private final int width;
  @Getter
  private final int height;
  private final List<Entity> boardElements;

  public EntityStore(int width, int height) {
    this.width = width;
    this.height = height;
    boardElements = Stream
      .iterate(0, UnaryOperator.identity())
      .limit(width * height)
      .map(pos -> new Floor())
      .collect(Collectors.toList());
  }

  public Entity getObjectAtPosition(Pos pos) {
    return boardElements.get(width * pos.getRow() + pos.getCol());
  }

  public void setObjectAtPosition(Entity entityToInsert, Pos pos) {
    boardElements.set(width * pos.getRow() + pos.getCol(), entityToInsert);
  }

  public Pos getPositionOf(Entity entity) {
    int indexOf = boardElements.indexOf(entity);
    return new Pos(indexOf % width, indexOf / width);
  }
}
