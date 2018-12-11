package de.moyapro.netfrag.entities;

import de.moyapro.netfrag.api.Entity;

/**
 * player itself
 */
public class Player implements Entity {

  @Override
  public String getRepresentingChar() {
    return "@";
  }
}
