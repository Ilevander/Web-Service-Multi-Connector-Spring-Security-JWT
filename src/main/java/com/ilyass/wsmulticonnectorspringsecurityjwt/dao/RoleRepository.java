package com.ilyass.wsmulticonnectorspringsecurityjwt.dao;

import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
    List<Role> findAll();
}
