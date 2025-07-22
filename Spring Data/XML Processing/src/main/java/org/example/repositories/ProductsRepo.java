package org.example.repositories;

import org.example.entities.Products;
import org.example.entities.dto.ProductsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;


@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    @Query("SELECT new org.example.entities.dto.ProductsDto(" +
            "p.name, p.price, " +
            "CASE " +
            "WHEN p.seller.firstName IS NOT NULL AND p.seller.lastName IS NOT NULL THEN CONCAT(p.seller.firstName, ' ', p.seller.lastName) " +
            "WHEN p.seller.firstName IS NOT NULL THEN p.seller.firstName " +
            "WHEN p.seller.lastName IS NOT NULL THEN p.seller.lastName " +
            "ELSE '' END" +
            ") " +
            "FROM Products p " +
            "WHERE p.buyer IS NULL AND p.price > :low AND p.price < :high " +
            "ORDER BY p.price ASC")
    Set<ProductsDto> findProductsNoBuyerRange(@Param("low") BigDecimal lowRange, @Param("high") BigDecimal highRange);
}