package de.moyapro.netfrag.entities;

import de.moyapro.netfrag.api.Impassable;

/**
 * Impassable board object
 */
public class Wall implements Impassable {

  @Override
  public String getRepresentingChar() {
    return "▓";
  }
}
