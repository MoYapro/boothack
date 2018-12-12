package de.moyapro.netboot.repository;

import de.moyapro.netboot.api.Entity;
import de.moyapro.netboot.entities.Pos;
import org.springframework.data.repository.Repository;


public interface GameRepository  extends Repository<Pos, Entity> {

  Entity save(Entity newEntity);
}
