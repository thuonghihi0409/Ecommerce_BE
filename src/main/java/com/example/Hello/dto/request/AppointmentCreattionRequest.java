package com.example.Hello.dto.request;

import com.example.Hello.entity.Customer;
import com.example.Hello.entity.Landlord;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCreattionRequest {
    private String appointmentDate;
    private String status;
    private String result;
    private String customerId;
    private String landlordId;
}
