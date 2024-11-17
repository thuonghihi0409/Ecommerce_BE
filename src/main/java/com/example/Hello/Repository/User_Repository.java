package com.example.Hello.Repository;

import com.example.Hello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends JpaRepository <User, String> {
    boolean existsByUsername(String username);
    User findByUsername (String username);
}
