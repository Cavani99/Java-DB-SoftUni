package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.entities.Categories;
import org.example.entities.Products;
import org.example.entities.Users;
import org.example.entities.dto.ProductsDto;
import org.example.entities.dto.UsersDto;
import org.example.entities.wrappers.CategoriesWrapper;
import org.example.entities.wrappers.ProductsDtoWrapper;
import org.example.entities.wrappers.ProductsWrapper;
import org.example.entities.wrappers.UsersWrapper;
import org.example.repositories.CategoriesRepo;
import org.example.repositories.ProductsRepo;
import org.example.repositories.UsersRepo;
import org.example.services.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Component
public class Main implements CommandLineRunner {
    private UsersRepo usersRepo;

    private UsersService usersService;
    private CategoriesRepo categoriesRepo;
    private ProductsRepo productsRepo;

    public Main(UsersService usersService, UsersRepo usersRepo, CategoriesRepo categoriesRepo, ProductsRepo productsRepo) {
        this.usersService = usersService;
        this.usersRepo = usersRepo;
        this.categoriesRepo = categoriesRepo;
        this.productsRepo = productsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        //importXml();
        productsInRange();
        //successfullySoldProducts();
    }

    private void importXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersWrapper.class);
        InputStream inputStream = getClass().getResourceAsStream("/files/users.xml");
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UsersWrapper users = (UsersWrapper) unmarshaller.unmarshal(bfr);

        //import users
        for (Users user : users.getUsers()) {
            usersRepo.save(user);
        }

        //import categories
        jaxbContext = JAXBContext.newInstance(CategoriesWrapper.class);
        inputStream = getClass().getResourceAsStream("/files/categories.xml");
        bfr = new BufferedReader(new InputStreamReader(inputStream));
        unmarshaller = jaxbContext.createUnmarshaller();
        CategoriesWrapper categories = (CategoriesWrapper) unmarshaller.unmarshal(bfr);

        for (Categories category : categories.getCategories()) {
            categoriesRepo.save(category);
        }

        //import products
        jaxbContext = JAXBContext.newInstance(ProductsWrapper.class);
        inputStream = getClass().getResourceAsStream("/files/products.xml");
        bfr = new BufferedReader(new InputStreamReader(inputStream));
        unmarshaller = jaxbContext.createUnmarshaller();
        ProductsWrapper products = (ProductsWrapper) unmarshaller.unmarshal(bfr);

        for (Products product : products.getProducts()) {
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

    private void productsInRange() throws JAXBException {
        Set<ProductsDto> products = productsRepo.findProductsNoBuyerRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        List<ProductsDto> productsDtos = new ArrayList<>(products);

        ProductsDtoWrapper wrapper = new ProductsDtoWrapper(productsDtos);

        JAXBContext context = JAXBContext.newInstance(ProductsDtoWrapper.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File file = new File("Spring Data/XML Processing/src/main/resources/files/export/products_in_range.xml");
        file.getParentFile().mkdirs();

        jaxbMarshaller.marshal(wrapper, file);
    }

    private void successfullySoldProducts() {
        List<UsersDto> users = usersService.getUsersWithSoldProducts();

        List<UsersDto> usersDtos = new ArrayList<>(users);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Optional: make the JSON output pretty
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File file = new File("Spring Data/JSON Processing/src/main/resources/files/export/successfully_sold_products.json");

            // Write the list to JSON
            objectMapper.writeValue(file, usersDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}