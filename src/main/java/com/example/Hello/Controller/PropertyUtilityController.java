package com.example.Hello.Controller;

import com.example.Hello.Service.PropertyUtilityService;
import com.example.Hello.dto.request.PropertyUtilityRequest;
import com.example.Hello.entity.PropertyUtility;
import com.example.Hello.entity.RentalProperty;
import com.example.Hello.entity.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-utilities")
public class PropertyUtilityController {

    @Autowired
    private PropertyUtilityService propertyUtilityService;

    // Tạo một liên kết mới giữa RentalProperty và Utility
    @PostMapping
    public PropertyUtility createPropertyUtility(@RequestBody PropertyUtilityRequest request) {
        return propertyUtilityService.createPropertyUtility(request);
    }

    // Lấy tất cả các PropertyUtility
    @GetMapping
    public List<PropertyUtility> getPropertyUtilities() {
        return propertyUtilityService.getPropertyUtility();
    }

    @GetMapping("/rentalpropertyid={id}")
    public List<Utility> getUtilities(@PathVariable String id){
        return  propertyUtilityService.getUtilitiesByRentalPropertyId(id);
    }
    @GetMapping("/utilityid={id}")
    public List<RentalProperty> getrentalbyutility(@PathVariable String id){
        return propertyUtilityService.getRentalPropertiesByUtilityId(id);
    }
    // Cập nhật liên kết giữa RentalProperty và Utility
    @PutMapping("/propertyutilityid={id}")
    public PropertyUtility updatePropertyUtility(@PathVariable String id, @RequestBody PropertyUtilityRequest request) {
        return propertyUtilityService.updatePropertyUtility(id, request);
    }

    // Xóa liên kết PropertyUtility theo id
    @DeleteMapping("/propertyUtilityid={propertyUtilityId}")
    public void deletePropertyUtility(@PathVariable String propertyUtilityId) {
        propertyUtilityService.deletePropertyUtility(propertyUtilityId);
    }
    @DeleteMapping("/propertyid={propertyId}")
    public void deletePropertyUtilitiesByProperty(@PathVariable String propertyId) {
        propertyUtilityService.deleteUtilitiesByRental(propertyId);
    }
}
