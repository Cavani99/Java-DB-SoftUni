package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public void shampoosBySize(Size size) {
        List<Shampoo> shampooList = shampooRepository.findBySizeOrderById(size);

        shampooList.forEach(shampoo -> System.out.printf("%s %s %.2flv.\n", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice()));
    }

    @Override
    public void shampoosBySizeOrLabel(Size size, long label) {
        List<Shampoo> shampooList = shampooRepository.findBySizeOrLabel_IdOrderByPriceAsc(size, label);

        shampooList.forEach(shampoo -> System.out.printf("%s %s %.2flv.\n", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice()));
    }

    @Override
    public void shampoosByPrice(BigDecimal price) {
        List<Shampoo> shampooList = shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);

        shampooList.forEach(shampoo -> System.out.printf("%s %s %.2flv.\n", shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice()));
    }
}
