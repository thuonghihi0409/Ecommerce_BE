package com.example.Hello.dto.request;

import com.example.Hello.entity.RentalProperty;
import com.example.Hello.entity.Utility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyUtilityRequest {
    private String rentalPropertyId;
    private String utilityId;
}
