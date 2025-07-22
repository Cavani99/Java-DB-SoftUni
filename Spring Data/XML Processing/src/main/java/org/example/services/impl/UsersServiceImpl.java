package org.example.services.impl;

import org.example.entities.Users;
import org.example.entities.dto.UsersDto;
import org.example.entities.dto.soldProductsDto;
import org.example.repositories.UsersRepo;
import org.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepo;

    @Autowired
    public UsersServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Transactional
    @Override
    public List<UsersDto> getUsersWithSoldProducts() {
        List<Users> sellers = usersRepo.soldProducts();

        List<UsersDto> userDtos = new ArrayList<>();

        for (Users seller : sellers) {
            Set<soldProductsDto> soldDtos = seller.getSoldProducts().stream()
                    .filter(p -> p.getBuyer() != null)
                    .map(p -> new soldProductsDto(
                            p.getName(),
                            p.getPrice(),
                            p.getBuyer().getFirstName(),
                            p.getBuyer().getLastName()))
                    .collect(Collectors.toSet());

            if (!soldDtos.isEmpty()) {
                userDtos.add(new UsersDto(
                        seller.getFirstName(),
                        seller.getLastName(),
                        soldDtos
                ));
            }
        }

        return userDtos;
    }
}
