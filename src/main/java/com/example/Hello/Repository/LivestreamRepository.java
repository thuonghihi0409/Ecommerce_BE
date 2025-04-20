package com.example.Hello.Repository;


import com.example.Hello.entity.Livestream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivestreamRepository extends JpaRepository<Livestream, Long> {
    Page<Livestream> findByStoreStoreId(Long storeId, Pageable pageable);
}
