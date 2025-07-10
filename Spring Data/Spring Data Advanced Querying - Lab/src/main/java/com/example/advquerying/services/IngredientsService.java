package com.example.advquerying.services;


import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IngredientsService  {
    void ingredientsStartingWith(String name);
    void ingredientsInStrings(Collection<String> names);
    void shampoosInGivenIngredients(List<String> strings);

    void shampoosWithIngredientsLessThan(int count);
    void deleteByName(String name);
    void updatePriceForIngredients(List<String> names);

    void updateByPriceForIngredients(BigDecimal price, List<String> names);
}
