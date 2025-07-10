package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientsRepository;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public void shampoosWithIngredientsLessThan(int count) {
        List<Shampoo> shampoos = ingredientsRepository.findByIngredientsCountLessThan(count);

        shampoos.forEach(s -> System.out.printf("%s\n", s.getBrand()));
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        ingredientsRepository.deleteByName(name);

        System.out.printf("%s Deleted!\n", name);
    }
}
