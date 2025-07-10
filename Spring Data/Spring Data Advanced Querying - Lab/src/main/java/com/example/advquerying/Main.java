package com.example.advquerying;

import com.example.advquerying.services.IngredientsService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientsService ingredientsService;

    public Main(ShampooService shampooService, IngredientsService ingredientsService) {
        this.shampooService = shampooService;
        this.ingredientsService = ingredientsService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //shampooService.shampoosBySize(Size.MEDIUM);
        //shampooService.shampoosBySizeOrLabel(Size.MEDIUM, 10);
        //shampooService.shampoosByPrice(BigDecimal.valueOf(5));
        //ingredientsService.ingredientsStartingWith("M");
        /*
        Collection<String> strings = new ArrayList<>();
        strings.add("Lavender");
        strings.add("Herbs");
        strings.add("Apple");

        ingredientsService.ingredientsInStrings(strings);*/
        shampooService.countShampoosLessThanPrice(BigDecimal.valueOf(8.50));
    }
}
