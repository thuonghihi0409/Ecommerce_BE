package com.example.Hello.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class PropertyUtility {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String propertyUtilityId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private RentalProperty rentalProperty;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;
}
