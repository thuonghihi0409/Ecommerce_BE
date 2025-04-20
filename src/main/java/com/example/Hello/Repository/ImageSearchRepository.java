package com.example.Hello.Repository;

import com.example.Hello.entity.ImageSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSearchRepository extends JpaRepository<ImageSearch, Long> {
    Page<ImageSearch> findByUserUserId(Long userId, Pageable pageable);
}
