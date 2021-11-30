package com.softtech.app.repository;

import com.softtech.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Object findByEmail(String email);

    User findByUserName(String username);
}
