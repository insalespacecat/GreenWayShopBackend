package com.greenwayshop.learning.api;

import com.greenwayshop.learning.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
}
