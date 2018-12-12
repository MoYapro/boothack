package de.moyapro.netboot.storeage;

import de.moyapro.netboot.api.Entity;
import de.moyapro.netboot.entities.EntityHelper;
import de.moyapro.netboot.entities.Floor;
import de.moyapro.netboot.entities.Player;
import de.moyapro.netboot.entities.Pos;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Store state of the board and provide access methods to manipulate that state
 */
@Service
@NoArgsConstructor
public class EntityStore {

  @Getter
  private int width;
  @Getter
  private int height;
  @Getter
  private Player player;
  private List<Entity> boardElements;

  public EntityStore loadMap(String mapToLoad) {
    initWidthAndHeightFromMap(mapToLoad);
    boardElements = mapToLoad.codePoints()
      .filter(this::isNoLineBreak)
      .mapToObj(EntityHelper::getInstanceForEntityChar)
      .collect(Collectors.toList());
    boardElements.stream().filter(e -> e instanceof Player).findAny().ifPresent(player -> this.player = (Player) player);
    return this;
  }

  private boolean isNoLineBreak(int c) {
    return "\n".codePointAt(0) != c;
  }

  private void initWidthAndHeightFromMap(String mapToLoad) {
    width = mapToLoad.indexOf("\n");
    height = mapToLoad.length() / width;
    if (-1 == width) {
      width = mapToLoad.length();
      height = 1;
    }
  }


  public Entity getObjectAtPosition(Pos pos) {
    return boardElements.get(width * pos.getRow() + pos.getCol());
  }

  public void setObjectAtPosition(Entity entityToInsert, Pos pos) {
    if (entityToInsert instanceof Player) {
      this.player = (Player) entityToInsert;
    }
    boardElements.set(width * pos.getRow() + pos.getCol(), entityToInsert);
  }

  public Pos getPositionOf(Entity entity) {
    int indexOf = boardElements.indexOf(entity);
    return new Pos(indexOf % width, indexOf / width);
  }

  public boolean isPositionOutsideOfMap(Pos newPosition) {
    return 0 > newPosition.getCol()
      || 0 > newPosition.getRow()
      || width <= newPosition.getCol()
      || height <= newPosition.getRow();
  }

  public EntityStore newGame(int width, int height) {
    this.width = width;
    this.height = height;
    boardElements = Stream
      .iterate(0, UnaryOperator.identity())
      .limit(width * height)
      .map(pos -> new Floor())
      .collect(Collectors.toList());
    return this;
  }
}
