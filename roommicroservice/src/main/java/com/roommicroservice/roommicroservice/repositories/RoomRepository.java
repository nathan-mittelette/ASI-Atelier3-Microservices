package com.roommicroservice.roommicroservice.repositories;

import com.roommicroservice.roommicroservice.models.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query(value = "SELECT * FROM public.room WHERE state = 'CREATED'", nativeQuery = true)
    List<Room> getAllCreated();
}
