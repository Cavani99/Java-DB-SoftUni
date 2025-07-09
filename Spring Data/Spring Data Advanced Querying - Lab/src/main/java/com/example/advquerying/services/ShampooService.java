package com.example.advquerying.services;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;

public interface ShampooService {
    void shampoosBySize(Size size);

    void shampoosBySizeOrLabel(Size size, long label);

    void shampoosByPrice(BigDecimal price);
}
