package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Categories;
import org.example.entities.Products;
import org.example.entities.Users;
import org.example.entities.dto.ProductsDto;
import org.example.repositories.CategoriesRepo;
import org.example.repositories.ProductsRepo;
import org.example.repositories.UsersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class Main implements CommandLineRunner {
    private UsersRepo usersRepo;
    private CategoriesRepo categoriesRepo;
    private ProductsRepo productsRepo;

    public Main(UsersRepo usersRepo, CategoriesRepo categoriesRepo, ProductsRepo productsRepo) {
        this.usersRepo = usersRepo;
        this.categoriesRepo = categoriesRepo;
        this.productsRepo = productsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        //importJson();
        productsInRange();
    }

    private void importJson() throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        //import users
        try (Reader reader = new InputStreamReader(
                getClass().getResourceAsStream("/files/users.json"), StandardCharsets.UTF_8)) {
            Users[] users = gson.fromJson(reader, Users[].class);

            for (Users user : users) {
                usersRepo.save(user);
            }
        }

        //import categories
        try (Reader reader = new InputStreamReader(
                getClass().getResourceAsStream("/files/categories.json"), StandardCharsets.UTF_8)) {
            Categories[] categories = gson.fromJson(reader, Categories[].class);

            for (Categories category : categories) {
                categoriesRepo.save(category);
            }
        }

        //import products
        try (Reader reader = new InputStreamReader(
                getClass().getResourceAsStream("/files/products.json"), StandardCharsets.UTF_8)) {
            Products[] products = gson.fromJson(reader, Products[].class);

            for (Products product : products) {
                int sold = new Random().nextInt(0, 2);

                if (sold == 1) {
                    long soldId = new Random().nextInt(1, (int) usersRepo.count());
                    Optional<Users> soldUser = usersRepo.findById(soldId);

                    soldUser.ifPresent(product::setBuyer);
                }

                long buyerId = new Random().nextInt(1, (int) usersRepo.count());
                Optional<Users> buyerUser = usersRepo.findById(buyerId);

                buyerUser.ifPresent(product::setSeller);

                Set<Categories> categoriesList = categoriesRepo.findRandomCategories(new Random().nextInt(0, 4));
                product.setCategories(categoriesList);

                productsRepo.save(product);
            }
        }
    }

    private void productsInRange() {
        Set<ProductsDto> products = productsRepo.findProductsNoBuyerRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        List<ProductsDto> productsDtos = new ArrayList<>(products);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Optional: make the JSON output pretty
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File file = new File("Spring Data/JSON Processing/src/main/resources/files/export/products_in_range.json");

            // Write the list to JSON
            objectMapper.writeValue(file, productsDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}