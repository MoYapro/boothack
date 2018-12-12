package de.moyapro.netboot.entities;

import de.moyapro.netboot.api.Entity;
import java.util.HashMap;
import java.util.Map;
import lombok.val;

public class EntityHelper {

  public static Map<Character, Class<? extends Entity>> getCharacterMap() {
    val map = new HashMap<Character, Class<? extends Entity>>();
    map.put('.', Floor.class);
    map.put('@', Player.class);
    map.put('▓', Wall.class);
    map.put('M', Monster.class);
    return map;
  }

  public static Entity getInstanceForEntityChar(int representation) {
    switch (representation) {
      case '@':
        return new Player();
      case 'M':
        return new Monster();
      case '▓':
        return new Wall();
    }
    return null;
  }
}
