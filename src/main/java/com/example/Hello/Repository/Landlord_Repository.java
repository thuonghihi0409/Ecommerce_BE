package com.example.Hello.Repository;

import com.example.Hello.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Landlord_Repository extends JpaRepository<Landlord,String> {
}
