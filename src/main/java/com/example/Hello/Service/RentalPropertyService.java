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
        rental.setStatus(request.getStatus());
        rental.setPostDate(request.getPostDate());
        rental.setUpdateDate(request.getUpdateDate());
        rental.setUrlmap(request.getUrlmap());
        rental.setPropertyName(request.getPropertyName());
        rental.setNumberViewer(request.getNumberViewer());
        rental.setLandlord( userRepository.findById(request.getLandlordId()).orElseThrow(() -> new RuntimeException("Loi tao rental")));
        return rentalPropertyRepository.save(rental);
    }

@Transactional
public RentalProperty updateRentalProperty(String id,RentalPropertyCreationRequest request){
        RentalProperty rental = rentalPropertyRepository.findById(id).orElseThrow(() -> new RuntimeException("not find rental") );
        rental.setArea(request.getArea());
        rental.setAddress(request.getAddress());
        rental.setAvailableRooms(request.getAvailableRooms());
        rental.setImages(request.getImages());
        rental.setElectricPrice(request.getElectricPrice());
        rental.setWaterPrice(request.getWaterPrice());
        rental.setRentPrice(request.getRentPrice());
        rental.setDescription(request.getDescription());
        rental.setStatus(request.getStatus());
        rental.setPostDate(request.getPostDate());
        rental.setUpdateDate(request.getUpdateDate());
        rental.setPropertyName(request.getPropertyName());
        rental.setNumberViewer(request.getNumberViewer());
        rental.setLandlord( userRepository.findById(request.getLandlordId()).orElseThrow(() -> new RuntimeException("Loi tao rental")));
        return rentalPropertyRepository.save(rental);
    }
    @Transactional
    public void updateRentalStatus(String id, int status) {
            // Find the rental property by ID
            RentalProperty rental = rentalPropertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental property not found"));

            // Update the status
            rental.setStatus(status);

            // Save the updated rental property back to the repository
            rentalPropertyRepository.save(rental);
        }
    public List<RentalProperty> getRental (){return rentalPropertyRepository.findAll();}
    public List <RentalProperty> getRentalByLandlord (String id){
        return rentalPropertyRepository.findByLandlord_Id(id);
    }
    public List <RentalProperty> getRentalByStatus (int id){
        return rentalPropertyRepository.findAllByStatus(id);
    }
    @Transactional
    public void deleteRental (String id){
        propertyUtillityRepository.deleteAllByRentalProperty_PropertyId(id);
        rentalPropertyRepository.deleteById(id);
    }

    public List<RentalProperty> getRentalByMonth(int month, int year) {
        return rentalPropertyRepository.findByMonthAndYear(month, year);
    }

    public List<RentalProperty> getTop3MostViewedProperties() {
        List<RentalProperty> properties = rentalPropertyRepository.findTop3ByOrderByNumberViewerDesc();
        return properties;
    }
}
