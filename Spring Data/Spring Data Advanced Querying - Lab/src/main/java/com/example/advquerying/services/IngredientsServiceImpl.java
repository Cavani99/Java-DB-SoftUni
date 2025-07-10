package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public void ingredientsStartingWith(String name) {
        List<Ingredient> ingredients = ingredientsRepository.findByNameStartingWith(name);

        ingredients.forEach(ingredient -> System.out.printf("%s\n", ingredient.getName()));
    }

    @Override
    public void ingredientsInStrings(Collection<String> names) {
        List<Ingredient> ingredients = ingredientsRepository.findByNameInOrderByPriceAsc(names);

        ingredients.forEach(ingredient -> System.out.printf("%s\n", ingredient.getName()));
    }

    @Override
    public void shampoosInGivenIngredients(List<String> strings) {
        Set<Ingredient> ingredients = new HashSet<>();
        for (String string : strings) {
            Ingredient ingredient = ingredientsRepository.findByName(string);
            ingredients.add(ingredient);
        }

        List<Shampoo> shampoos = ingredientsRepository.findByIngredientsIn(ingredients);

        shampoos.forEach(s -> System.out.printf("%s\n", s.getBrand()));
    }
}
