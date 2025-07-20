package org.example.repositories;


import org.example.entities.Users;
import org.example.entities.dto.ProductsDto;
import org.example.entities.dto.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    @Query("SELECT DISTINCT u FROM Users u JOIN u.soldProducts p WHERE p.buyer IS NOT NULL")
    List<Users> soldProducts();
}
