package com.example.Hello.Service;

import com.example.Hello.Repository.User_Repository;
import com.example.Hello.Repository.Utility_Repository;
import com.example.Hello.dto.request.UtilityCreateRequest;
import com.example.Hello.entity.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityService {
    @Autowired
    private Utility_Repository utilityRepository;

    public Utility createUtility(UtilityCreateRequest request) {
        Utility utility = new Utility();
        utility.setUtilityName(request.getUtilityName());
        utility.setIcon(request.getIcon());
        return utilityRepository.save(utility);
    }

    public List<Utility> getUtilities() {
        return utilityRepository.findAll();
    }

    public Utility updateUtility(String utilityId, UtilityCreateRequest request) {
        Utility utility = utilityRepository.findById(utilityId).orElseThrow(() -> new RuntimeException("Loi khong tim thay Utility"));
        return utilityRepository.save(utility);
    }

    public void deleteUtility (String utilityId ){
        utilityRepository.deleteById(utilityId);
    }

}
