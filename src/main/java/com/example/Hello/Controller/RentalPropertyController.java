package com.example.Hello.Controller;

import com.example.Hello.Service.PropertyUtilityService;
import com.example.Hello.Service.RentalPropertyService;
import com.example.Hello.dto.request.RentalPropertyCreationRequest;
import com.example.Hello.entity.RentalProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


    @GetMapping("/status={status}")
    List <RentalProperty> getRentalByStatus (@PathVariable int status){
        return rentalPropertyService.getRentalByStatus(status);
    }



    @PutMapping("/rentalpropertyid={rentalpropertyId}")
    String updateRental (@PathVariable String rentalpropertyId , @RequestBody RentalPropertyCreationRequest request){
        rentalPropertyService.updateRentalProperty(rentalpropertyId, request);
        return "has beeen update rental";
    }

    @PatchMapping("/rentalpropertyid={rentalpropertyId}/status/{status}")
    public String updateRentalStatus(@PathVariable String rentalpropertyId, @PathVariable int status) {
        rentalPropertyService.updateRentalStatus(rentalpropertyId, status);
        return "Rental property status has been updated to " + status;
    }

    @GetMapping("/month={month}&year={year}")
    public List<RentalProperty> getRentalByMonth(@PathVariable int month, @PathVariable int year) {
        return rentalPropertyService.getRentalByMonth(month, year);
    }

    @GetMapping("/gettoprentalproperty")
    public List<RentalProperty> getTop3MostViewedProperties() {
        return rentalPropertyService.getTop3MostViewedProperties();
    }
}
