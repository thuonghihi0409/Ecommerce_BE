package com.example.Hello.Repository;

import com.example.Hello.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Appointment_Repository extends JpaRepository<Appointment,String> {
    List<Appointment> findAllByCustomer_Id(String id);
}
