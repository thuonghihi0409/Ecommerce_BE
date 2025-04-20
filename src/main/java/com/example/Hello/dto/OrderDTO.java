package com.example.Hello.dto;



import com.example.Hello.entity.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private Long buyerId;
    private Long storeId;
    private Long branchId;
    private Double totalAmount;
    private OrderStatus status;
    private String shippingAddress;
    private List<OrderItemDTO> orderItems;
}

