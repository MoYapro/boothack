package de.moyapro.netboot.entities;

import de.moyapro.netboot.api.Entity;

/**
 * a piece of ground
 */
public class Floor implements Entity {

  @Override
  public String getRepresentingChar() {
    return ".";
  }
}
