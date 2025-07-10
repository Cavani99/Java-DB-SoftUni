package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);

    List<Ingredient> findByNameStartingWith(String start);

    List<Ingredient> findByNameInOrderByPriceAsc(Collection<String> list);

    @Query(value = "select s from Shampoo s " +
            "join s.ingredients i where i in :ingredients")
    List<Shampoo> findByIngredientsIn(@Param(value = "ingredients")
                                      Set<Ingredient> ingredients);

    @Query("SELECT s FROM Shampoo s " +
            "JOIN s.ingredients i " +
            "GROUP BY s " +
            "HAVING COUNT(i) < :count")
    List<Shampoo> findByIngredientsCountLessThan(@Param("count") long count);

    @Modifying
    @Query("DELETE FROM Ingredient as i " +
            "WHERE i.name = :name")
    int deleteByName(@Param("name") String name);

    @Modifying
    @Query("UPDATE Ingredient as i " +
            "SET i.price = i.price + i.price * 0.1" +
            "WHERE i.name IN :names")
    int updateByNames(@Param("names") List<String> names);
}
