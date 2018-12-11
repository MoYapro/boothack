package de.moyapro.netfrag.entities;

import de.moyapro.netfrag.api.Entity;

/**
 * a piece of ground
 */
public class Floor implements Entity {

  @Override
  public String getRepresentingChar() {
    return ".";
  }
}
