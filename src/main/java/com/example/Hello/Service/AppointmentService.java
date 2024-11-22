package com.example.Hello.Service;

import com.example.Hello.Repository.Appointment_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.AppointmentCreattionRequest;
import com.example.Hello.dto.request.AppointmentUpdate;
import com.example.Hello.entity.Appointment;
import com.example.Hello.entity.Customer;
import com.example.Hello.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Appointment_Repository appointmentRepository;

    public Appointment createAppointment (AppointmentCreattionRequest request){
        Appointment appointment= new Appointment();
        User landlord = userRepository.findById(request.getLandlordId()).orElseThrow(()-> new RuntimeException("User not found"));
        User customer= userRepository.findById(request.getCustomerId()).orElseThrow(()-> new RuntimeException("User not found"));
        appointment.setLandlord(landlord);
        appointment.setCustomer(customer);
        appointment.setResult(request.getResult());
        appointment.setStatus(request.getStatus());
        appointment.setAppointmentDate(request.getAppointmentDate());
        return appointmentRepository.save(appointment);

    }

    @Transactional
    public Appointment updateAppointment (AppointmentUpdate request){
        Appointment appointment= appointmentRepository.findById(request.getAppointmentId()).orElseThrow(()-> new RuntimeException("Appoint not found"));
        User landlord = userRepository.findById(request.getLandlordId()).orElseThrow(()-> new RuntimeException("User not found"));
        User customer= userRepository.findById(request.getCustomerId()).orElseThrow(()-> new RuntimeException("User not found"));
        appointment.setLandlord(landlord);
        appointment.setCustomer(customer);
        appointment.setResult(request.getResult());
        appointment.setStatus(request.getStatus());
        appointment.setAppointmentDate(request.getAppointmentDate());
        return appointmentRepository.save(appointment);
    }
    public List <Appointment> getAppointment (){
        return appointmentRepository.findAll();
    }

    public List <Appointment> getAppointmentByCustomer (String id){
        return appointmentRepository.findAllByCustomer_Id(id);
    }
}
