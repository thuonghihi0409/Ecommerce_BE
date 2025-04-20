package com.example.Hello.Mapper;


import com.example.Hello.dto.ReviewDTO;
import com.example.Hello.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "buyerId", source = "buyer.userId")
    ReviewDTO toDTO(Review review);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Review toEntity(ReviewDTO reviewDTO);
}

