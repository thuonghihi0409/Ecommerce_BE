package com.example.Hello.Repository;

import com.example.Hello.entity.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Utility_Repository extends JpaRepository<Utility,String> {
}
