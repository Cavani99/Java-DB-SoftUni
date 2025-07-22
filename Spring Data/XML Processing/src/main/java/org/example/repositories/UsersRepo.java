package org.example.repositories;


import org.example.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    @Query("SELECT DISTINCT u FROM Users u JOIN u.soldProducts p WHERE p.buyer IS NOT NULL")
    List<Users> soldProducts();
}
