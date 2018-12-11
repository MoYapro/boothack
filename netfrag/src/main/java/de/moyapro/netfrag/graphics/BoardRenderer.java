package de.moyapro.netfrag.graphics;

import static de.moyapro.netfrag.api.PredefinedFunction.increment;

import de.moyapro.netfrag.api.Entity;
import de.moyapro.netfrag.entities.Pos;
import de.moyapro.netfrag.storeage.EntityStore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Create outputString representing the state of the game
 */
public class BoardRenderer {

  public String render(EntityStore entityStore) {
    return Stream
      .iterate(0, increment)
      .limit(entityStore.getHeight())
      .map(rowNumber -> getLine(entityStore, rowNumber))
      .collect(Collectors.joining("\n"));
  }

  private String getLine(EntityStore entityStore, Integer rowNumber) {
    return Stream
      .iterate(0, increment)
      .limit(entityStore.getWidth())
      .map(colNumber -> renderElementAt(entityStore, new Pos(colNumber, rowNumber)))
      .collect(Collectors.joining());
  }

  private String renderElementAt(EntityStore entityStore, Pos pos) {
    Entity objectAtPosition = entityStore.getObjectAtPosition(pos);
    if (null == objectAtPosition) {
      return ".";
    }
    return objectAtPosition.getRepresentingChar();
  }
}
