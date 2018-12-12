package de.moyapro.netboot.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * An object representing a location in the world
 */
@Data
@Setter(AccessLevel.NONE)
public class Pos {

  private final int col;
  private final int row;

  public Pos incrementCol() {
    return new Pos(col + 1, row);
  }

  public Pos decrementCol() {
    return new Pos(col - 1, row);
  }

  public Pos decrementRow() {
    return new Pos(col, row - 1);
  }

  public Pos incrementRow() {
    return new Pos(col, row + 1);
  }
}
