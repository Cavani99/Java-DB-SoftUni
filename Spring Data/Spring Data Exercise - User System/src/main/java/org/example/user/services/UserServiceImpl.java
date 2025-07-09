package org.example.user.services;

import org.example.user.models.User;
import org.example.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void findUsersByEmailProvider(String provider) {
        List<User> userList = userRepository.findAll();

        userList.forEach(user -> {
                    String emailProvider = user.getEmail().split("@")[1];

                    if (emailProvider.equals(provider)) {
                        System.out.println(user.getUsername() + " " + user.getEmail());
                    }
                }
        );
    }

    @Override
    public void deleteOldUsers(LocalDateTime time) {
        List<User> users = userRepository.findByLastTimeLoggedBefore(time);

        users.forEach(user -> {
            user.setIs_deleted(true);
            userRepository.save(user);
        });

        System.out.println("Deleted users: " + users.size());

        userRepository.deleteAll(users);
    }
}
