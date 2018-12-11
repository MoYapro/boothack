package de.moyapro.netfrag.game;

import de.moyapro.netfrag.api.Impassable;
import de.moyapro.netfrag.api.MoveAction;
import de.moyapro.netfrag.entities.Floor;
import de.moyapro.netfrag.entities.Player;
import de.moyapro.netfrag.entities.Pos;
import de.moyapro.netfrag.storeage.EntityStore;

class MoveEngine {


  void move(Player player, MoveAction action, EntityStore entityStore) {
    Pos playerPosition = entityStore.getPositionOf(player);
    Pos newPosition = action.apply(playerPosition);
    if (entityStore.getObjectAtPosition(newPosition) instanceof Impassable) {
      return;
    }
    entityStore.setObjectAtPosition(new Floor(), playerPosition);
    entityStore.setObjectAtPosition(player, newPosition);
  }
}
