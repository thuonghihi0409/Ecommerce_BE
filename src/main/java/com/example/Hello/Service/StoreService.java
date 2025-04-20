package com.example.Hello.Service;


import com.example.Hello.Mapper.StoreMapper;
import com.example.Hello.Repository.StoreRepository;
import com.example.Hello.Repository.UserRepository;
import com.example.Hello.dto.StoreDTO;
import com.example.Hello.entity.Store;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreMapper storeMapper;

    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = storeMapper.toEntity(storeDTO);
        store.setSeller(userRepository.findById(storeDTO.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found")));
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());
        return storeMapper.toDTO(storeRepository.save(store));
    }

    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setSeller(userRepository.findById(storeDTO.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found")));
        store.setStoreName(storeDTO.getStoreName());
        store.setDescription(storeDTO.getDescription());
        store.setLogoUrl(storeDTO.getLogoUrl());
        store.setUpdatedAt(LocalDateTime.now());
        return storeMapper.toDTO(storeRepository.save(store));
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public StoreDTO getStoreById(Long id) {
        return storeMapper.toDTO(storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found")));
    }

    public Page<StoreDTO> getStoresBySeller(Long sellerId, Pageable pageable) {
        return storeRepository.findBySellerUserId(sellerId, pageable).map(storeMapper::toDTO);
    }

    public Page<StoreDTO> getAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable).map(storeMapper::toDTO);
    }
}