package com.example.Hello.Repository;

import com.example.Hello.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Admin_Repository extends JpaRepository<Admin, String> {

}
