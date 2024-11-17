package com.example.Hello.Controller;

import com.example.Hello.Service.PropertyUtilityService;
import com.example.Hello.Service.RentalPropertyService;
import com.example.Hello.dto.request.RentalPropertyCreationRequest;
import com.example.Hello.entity.RentalProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentalproperty")
public class RentalPropertyController {
    @Autowired
    private RentalPropertyService rentalPropertyService;

    @Autowired
    private PropertyUtilityService propertyUtilityService;
    @GetMapping
    List<RentalProperty> getRental() {
        return rentalPropertyService.getRental();
    }
    @GetMapping("/landlord={id}")
    List <RentalProperty> getRentalByLandlord (@PathVariable String id){
        return rentalPropertyService.getRentalByLandlord(id);
    }
    @PostMapping
    RentalProperty createRental(@RequestBody RentalPropertyCreationRequest request) {
        return rentalPropertyService.createRentalProperty(request);
    }

    @DeleteMapping("/rentalpropertyid={rentalpropertyId}")
    String deleteRental (@PathVariable String rentalpropertyId){
        rentalPropertyService.deleteRental(rentalpropertyId);
        return "has beeen Delate rental";
    }
}
