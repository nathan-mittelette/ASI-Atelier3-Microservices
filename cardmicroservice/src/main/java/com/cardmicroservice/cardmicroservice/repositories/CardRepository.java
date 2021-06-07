package com.cardmicroservice.cardmicroservice.repositories;

import com.cardmicroservice.cardmicroservice.models.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    @Query(value = "SELECT * FROM public.card WHERE available is TRUE", nativeQuery = true)
    List<Card> findAllAvailable();
}
