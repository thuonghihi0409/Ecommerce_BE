package com.example.Hello.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class RentalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String propertyId;
    private String propertyName;
    private String address;
    private String rentPrice;
    private String description;
    private float area;
    private int availableRooms;
    private String waterPrice;
    private String electricPrice;
    @ElementCollection
    private List<String> images = new ArrayList<>();
    private Date postDate;
    private Date updateDate;
    private int status;
    private long numberViewer;
    private String urlmap;
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;


}

