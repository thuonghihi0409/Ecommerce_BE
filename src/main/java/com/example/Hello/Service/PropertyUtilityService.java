package com.example.Hello.Service;


import com.example.Hello.Repository.PropertyUtillity_Repository;
import com.example.Hello.Repository.RentalProperty_Repository;

import com.example.Hello.Repository.Utility_Repository;
import com.example.Hello.dto.request.PropertyUtilityRequest;
import com.example.Hello.entity.PropertyUtility;
import com.example.Hello.entity.RentalProperty;
import com.example.Hello.entity.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyUtilityService {

    @Autowired
    private RentalProperty_Repository rentalPropertyRepository;

    @Autowired
    private Utility_Repository utilityRepository;
    @Autowired
    private PropertyUtillity_Repository propertyUtillityRepository;

    // Tạo một liên kết mới giữa RentalProperty và Utility
    public PropertyUtility createPropertyUtility(PropertyUtilityRequest request) {
        RentalProperty rentalProperty = rentalPropertyRepository.findById(request.getRentalPropertyId())
                .orElseThrow(() -> new RuntimeException("Rental Property not found"));
        Utility utility = utilityRepository.findById(request.getUtilityId())
                .orElseThrow(() -> new RuntimeException("Utility not found"));
        PropertyUtility propertyUtility= new PropertyUtility();
        propertyUtility.setUtility(utility);
        propertyUtility.setRentalProperty(rentalProperty);
        return propertyUtillityRepository.save(propertyUtility);
    }

    // Lấy một RentalProperty cùng với Utilities của nó
    public List<PropertyUtility> getPropertyUtility() {
        return propertyUtillityRepository.findAll();
    }

    public List<Utility> getUtilitiesByRentalPropertyId(String rentalPropertyId) {
        // Lấy danh sách PropertyUtility theo rentalPropertyId
        List<PropertyUtility> propertyUtilities = propertyUtillityRepository.findAllByRentalProperty_PropertyId(rentalPropertyId);

        // Chuyển đổi sang danh sách Utility
        List<Utility> utilities = propertyUtilities.stream()
                .map(PropertyUtility::getUtility) // Lấy ra đối tượng Utility từ PropertyUtility
                .collect(Collectors.toList());

        return utilities;
    }

    public List<RentalProperty> getRentalPropertiesByUtilityId(String utilityId) {
        return propertyUtillityRepository.findRentalPropertiesByUtilityId(utilityId);
    }
    public PropertyUtility updatePropertyUtility(String propertyUtilityId, PropertyUtilityRequest request) {
        RentalProperty rentalProperty = rentalPropertyRepository.findById(request.getRentalPropertyId())
                .orElseThrow(() -> new RuntimeException("Rental Property not found"));
        Utility utility = utilityRepository.findById(request.getUtilityId())
                .orElseThrow(() -> new RuntimeException("Utility not found"));
        PropertyUtility propertyUtility= propertyUtillityRepository.findById(propertyUtilityId).orElseThrow(() -> new RuntimeException("PropertyUtility not found"));
        propertyUtility.setUtility(utility);
        propertyUtility.setRentalProperty(rentalProperty);
        return propertyUtillityRepository.save(propertyUtility);
    }

    // Xóa Utility khỏi RentalProperty
    public void deletePropertyUtility(String propertyUtilityId) {
        propertyUtillityRepository.deleteById(propertyUtilityId);
    }
    public  void deleteUtilitiesByRental (String id) {
        propertyUtillityRepository.deleteAllByRentalProperty_PropertyId(id);
    }
}
