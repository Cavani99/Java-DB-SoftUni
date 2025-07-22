package org.example.services;

import org.example.entities.dto.UsersDto;

import java.util.List;

public interface UsersService {

    List<UsersDto> getUsersWithSoldProducts();
}
