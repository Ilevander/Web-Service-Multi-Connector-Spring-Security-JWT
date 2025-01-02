package com.ilyass.wsmulticonnectorspringsecurityjwt.dao;

import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
