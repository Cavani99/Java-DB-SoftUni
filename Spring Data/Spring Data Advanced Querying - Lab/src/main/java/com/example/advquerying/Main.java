package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;

    public Main(ShampooService shampooService){
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        //shampooService.shampoosBySize(Size.MEDIUM);
        //shampooService.shampoosBySizeOrLabel(Size.MEDIUM, 10);
        shampooService.shampoosByPrice(BigDecimal.valueOf(5));
    }
}
