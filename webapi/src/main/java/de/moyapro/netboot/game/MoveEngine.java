package de.moyapro.netboot.game;

import de.moyapro.netboot.api.Impassable;
import de.moyapro.netboot.api.MoveAction;
import de.moyapro.netboot.entities.Floor;
import de.moyapro.netboot.entities.Player;
import de.moyapro.netboot.entities.Pos;
import de.moyapro.netboot.storeage.EntityStore;
import org.springframework.stereotype.Service;

@Service
class MoveEngine {


  void move(Player player, MoveAction action, EntityStore entityStore) {
    Pos playerPosition = entityStore.getPositionOf(player);
    Pos newPosition = action.apply(playerPosition);
    if (entityStore.isPositionOutsideOfMap(newPosition)) {
      return;
    }
    if (entityStore.getObjectAtPosition(newPosition) instanceof Impassable) {
      return;
    }
    entityStore.setObjectAtPosition(new Floor(), playerPosition);
    entityStore.setObjectAtPosition(player, newPosition);
  }
}
