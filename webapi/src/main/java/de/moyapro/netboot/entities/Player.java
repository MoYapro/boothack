package de.moyapro.netboot.entities;

import de.moyapro.netboot.api.Entity;

/**
 * player itself
 */
public class Player implements Entity {

  @Override
  public String getRepresentingChar() {
    return "@";
  }
}
