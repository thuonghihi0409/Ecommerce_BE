package com.example.Hello.Repository;

import com.example.Hello.entity.RentalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalProperty_Repository extends JpaRepository<RentalProperty,String> {
    List<RentalProperty> findByLandlord_Id(String id);

    List<RentalProperty> findAllByStatus(int status);

    void deleteByLandlord_Id(String id);

    @Query("SELECT r FROM RentalProperty r WHERE MONTH(r.postDate) = :month AND YEAR(r.postDate) = :year")
    List<RentalProperty> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT rp FROM RentalProperty rp ORDER BY rp.numberViewer DESC")
    List<RentalProperty> findTop3ByOrderByNumberViewerDesc();
}
