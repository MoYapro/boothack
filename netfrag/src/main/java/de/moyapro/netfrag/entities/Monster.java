package de.moyapro.netfrag.entities;

import de.moyapro.netfrag.api.Entity;

public class Monster implements Entity {

  @Override
  public String getRepresentingChar() {
    return "M";
  }
}
