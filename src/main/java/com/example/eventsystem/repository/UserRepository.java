/*
    The class which is used to do the query requests involving users
*/
package com.example.eventsystem.repository;

import com.example.eventsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // this is interface is responsible for data access
public interface UserRepository extends JpaRepository<User, Long> {

    @Query
    Optional<User> findUserByEmail(String email);

    @Query
    Optional<User> findUserById(Long userId);


}