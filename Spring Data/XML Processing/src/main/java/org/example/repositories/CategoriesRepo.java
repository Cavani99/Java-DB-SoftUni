package org.example.repositories;

import org.example.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    Set<Categories> findRandomCategories(@Param("limit") int limit);
}
