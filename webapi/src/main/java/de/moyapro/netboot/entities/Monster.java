package de.moyapro.netboot.entities;

import de.moyapro.netboot.api.Entity;

public class Monster implements Entity {

  @Override
  public String getRepresentingChar() {
    return "M";
  }
}
