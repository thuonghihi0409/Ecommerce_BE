package com.example.Hello.Repository;


import com.example.Hello.entity.StoreBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreBranchRepository extends JpaRepository<StoreBranch, Long> {
    Page<StoreBranch> findByStoreStoreId(Long storeId, Pageable pageable);
}
