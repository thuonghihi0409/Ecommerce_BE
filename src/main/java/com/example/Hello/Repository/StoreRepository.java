package com.example.Hello.Repository;



import com.example.Hello.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findBySellerUserId(Long sellerId, Pageable pageable);
}