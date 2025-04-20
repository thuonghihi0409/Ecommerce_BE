package com.example.Hello.Service;

import com.example.Hello.Mapper.OrderItemMapper;
import com.example.Hello.Mapper.OrderMapper;
import com.example.Hello.Repository.*;
import com.example.Hello.dto.OrderDTO;
import com.example.Hello.dto.OrderItemDTO;
import com.example.Hello.entity.Order;
import com.example.Hello.entity.OrderItem;
import com.example.Hello.entity.OrderStatus;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final StoreBranchRepository storeBranchRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setBuyer(userRepository.findById(orderDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found")));
        order.setStore(storeRepository.findById(orderDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        order.setBranch(storeBranchRepository.findById(orderDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found")));
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem item = orderItemMapper.toEntity(itemDTO);
            item.setOrder(savedOrder);
            item.setVariant(productVariantRepository.findById(itemDTO.getVariantId())
                    .orElseThrow(() -> new RuntimeException("ProductVariant not found")));
            orderItemRepository.save(item);
        }

        return orderMapper.toDTO(savedOrder);
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderMapper.toDTO(orderRepository.save(order));
    }

    public OrderDTO cancelOrder(Long id) {
        return updateOrderStatus(id, OrderStatus.CANCELLED);
    }

    public OrderDTO getOrderById(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found")));
    }

    public Page<OrderDTO> getOrdersByBuyer(Long buyerId, Pageable pageable) {
        return orderRepository.findByBuyerUserId(buyerId, pageable).map(orderMapper::toDTO);
    }

    public Page<OrderDTO> getOrdersByStore(Long storeId, Pageable pageable) {
        return orderRepository.findByStoreStoreId(storeId, pageable).map(orderMapper::toDTO);
    }
}
