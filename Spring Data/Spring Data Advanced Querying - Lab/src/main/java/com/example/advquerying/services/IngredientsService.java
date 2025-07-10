package com.example.advquerying.services;


import com.example.advquerying.entities.Ingredient;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IngredientsService  {
    void ingredientsStartingWith(String name);
    void ingredientsInStrings(Collection<String> names);
    void shampoosInGivenIngredients(List<String> strings);

    void shampoosWithIngredientsLessThan(int count);
    void deleteByName(String name);
}
