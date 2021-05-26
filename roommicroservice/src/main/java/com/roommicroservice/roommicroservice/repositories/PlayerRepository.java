package com.roommicroservice.roommicroservice.repositories;

import com.roommicroservice.roommicroservice.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
