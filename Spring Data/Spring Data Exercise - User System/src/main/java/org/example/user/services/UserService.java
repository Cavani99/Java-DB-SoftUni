package org.example.user.services;

import java.time.LocalDateTime;

public interface UserService {
    void findUsersByEmailProvider(String provider);
    void deleteOldUsers(LocalDateTime time);
}
