package com.example.advquerying.services;


import java.util.Collection;

public interface IngredientsService  {
    void ingredientsStartingWith(String name);
    void ingredientsInStrings(Collection<String> names);
}
