package com.usermicroservice.usermicroservice.repositories;

import com.usermicroservice.usermicroservice.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /*@Query(
            value = "select * from incident where intensity = ?1 and incident_type = ?2 and map_item_id = ?3",
            nativeQuery = true)
    Collection<User> findByCompleteData(float intensity, String type, int mapItemId);

    @Query(
            value = "select * from incident where incident_type = ?1 and map_item_id = ?2",
            nativeQuery = true)
    Collection<User> findByData(String type, int mapItemId);


    @Query("SELECT i FROM Incident i LEFT JOIN MapItem mi ON i.mapItem = mi WHERE mi.posX = :posX AND mi.posY = :posY AND i.incidentType = :incidentType")
    Optional<User> findFromString(@Param("incidentType") IncidentType incidentType, @Param("posX") int posX, @Param("posY") int posY);*/

    Optional<User> findByEmail(String email);

    @Query("SELECT u.money FROM User u WHERE u.id = :userId")
    Long getMoney(@Param("userId") Long userId);
}
