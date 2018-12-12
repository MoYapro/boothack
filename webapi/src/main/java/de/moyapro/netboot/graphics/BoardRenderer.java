package de.moyapro.netboot.graphics;

import de.moyapro.netboot.api.Entity;
import de.moyapro.netboot.entities.Pos;
import de.moyapro.netboot.storeage.EntityStore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

/**
 * Create outputString representing the state of the game
 */
@Service
public class BoardRenderer {

  public String render(EntityStore entityStore) {
    return IntStream
      .range(0, entityStore.getHeight())
      .mapToObj(rowNumber -> getLine(entityStore, rowNumber))
      .collect(Collectors.joining("\n"));
  }

  private String getLine(EntityStore entityStore, Integer rowNumber) {
    return IntStream
      .range(0, entityStore.getWidth())
      .mapToObj(colNumber -> renderElementAt(entityStore, new Pos(colNumber, rowNumber)))
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
