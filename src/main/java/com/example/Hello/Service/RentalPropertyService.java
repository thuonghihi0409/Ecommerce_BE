package com.example.Hello.Service;

import com.example.Hello.Mapper.RentalPropertyMapper;
import com.example.Hello.Repository.PropertyUtillity_Repository;
import com.example.Hello.Repository.RentalProperty_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.RentalPropertyCreationRequest;
import com.example.Hello.entity.RentalProperty;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalPropertyService {
    @Autowired
    private RentalProperty_Repository rentalPropertyRepository;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private PropertyUtillity_Repository propertyUtillityRepository;
    public RentalProperty createRentalProperty(RentalPropertyCreationRequest request){
        RentalProperty rental = new RentalProperty();
        rental.setArea(request.getArea());
        rental.setAddress(request.getAddress());
        rental.setAvailableRooms(request.getAvailableRooms());
        rental.setImages(request.getImages());
        rental.setElectricPrice(request.getElectricPrice());
        rental.setWaterPrice(request.getWaterPrice());
        rental.setRentPrice(request.getRentPrice());
        rental.setDescription(request.getDescription());
        rental.setPostDate(request.getPostDate());
        rental.setUpdateDate(request.getUpdateDate());
        rental.setPropertyName(request.getPropertyName());
        rental.setLandlord( userRepository.findById(request.getLandlordId()).orElseThrow(() -> new RuntimeException("Loi tao rental")));
        return rentalPropertyRepository.save(rental);
    }
    public List<RentalProperty> getRental (){return rentalPropertyRepository.findAll();}
    public List <RentalProperty> getRentalByLandlord (String id){
        return rentalPropertyRepository.findByLandlord_Id(id);
    }
    @Transactional
    public void deleteRental (String id){
        propertyUtillityRepository.deleteAllByRentalProperty_PropertyId(id);
        rentalPropertyRepository.deleteById(id);
    }
}
