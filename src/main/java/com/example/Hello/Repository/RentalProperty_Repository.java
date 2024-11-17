package com.example.Hello.Repository;

import com.example.Hello.entity.RentalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalProperty_Repository extends JpaRepository<RentalProperty,String> {
    List<RentalProperty> findByLandlord_Id (String id);
    void deleteByLandlord_Id (String id);
}
