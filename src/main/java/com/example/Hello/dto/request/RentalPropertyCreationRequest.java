package com.example.Hello.dto.request;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RentalPropertyCreationRequest {
    private String propertyName;
    private String address;
    private String rentPrice;
    private String description;
    private float area;
    private int availableRooms;
    private String waterPrice;
    private String electricPrice;
    private List<String> images = new ArrayList<>();
    private Date postDate;
    private Date updateDate;
    private String landlordId ;
}
