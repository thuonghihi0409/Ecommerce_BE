package com.example.Hello.Controller;

import com.example.Hello.Service.AppointmentService;
import com.example.Hello.dto.request.AppointmentCreattionRequest;
import com.example.Hello.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping
    Appointment createAppointment (@RequestBody AppointmentCreattionRequest request){
        return appointmentService.createAppointment(request);
    }
    @GetMapping
    List<Appointment> getAppointments (){
        return appointmentService.getAppointment();
    }
}
