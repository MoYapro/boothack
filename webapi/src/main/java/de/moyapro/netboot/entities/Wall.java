package de.moyapro.netboot.entities;

import de.moyapro.netboot.api.Impassable;

/**
 * Impassable board object
 */
public class Wall implements Impassable {

  @Override
  public String getRepresentingChar() {
    return "▓";
  }
}
