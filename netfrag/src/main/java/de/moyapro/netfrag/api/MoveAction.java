package de.moyapro.netfrag.api;

import de.moyapro.netfrag.entities.Pos;
import java.util.function.UnaryOperator;

/**
 * Calculate new position aka handleAction
 */
@FunctionalInterface
public interface MoveAction extends UnaryOperator<Pos> {

  MoveAction LEFT = Pos::decrementCol;
  MoveAction RIGHT = Pos::incrementCol;
  MoveAction UP = Pos::decrementRow;
  MoveAction DOWN = Pos::incrementRow;

  @Override
  Pos apply(Pos pos);
}
