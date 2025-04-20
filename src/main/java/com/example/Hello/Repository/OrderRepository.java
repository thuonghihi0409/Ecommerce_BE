package com.example.Hello.Repository;


import com.example.Hello.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByBuyerUserId(Long buyerId, Pageable pageable);
    Page<Order> findByStoreStoreId(Long storeId, Pageable pageable);
}

