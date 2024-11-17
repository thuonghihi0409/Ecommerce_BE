package com.example.Hello.Repository;

import com.example.Hello.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_Repository extends JpaRepository<Customer,String> {

}
