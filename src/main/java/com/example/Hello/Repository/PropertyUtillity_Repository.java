package com.example.Hello.Repository;

import com.example.Hello.entity.PropertyUtility;
import com.example.Hello.entity.RentalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyUtillity_Repository extends JpaRepository<PropertyUtility,String> {
    List<PropertyUtility> findAllByRentalProperty_PropertyId (String id);
    @Query("SELECT pu.rentalProperty FROM PropertyUtility pu WHERE pu.utility.utilityId = :utilityId")
    List<RentalProperty> findRentalPropertiesByUtilityId(@Param("utilityId") String utilityId);

    void deleteAllByRentalProperty_PropertyId(String id);
}
