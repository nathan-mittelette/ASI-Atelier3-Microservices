package com.usermicroservice.usermicroservice.repositories;

import com.usermicroservice.usermicroservice.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.money FROM User u WHERE u.id = :userId")
    Long getMoney(@Param("userId") Long userId);
}
