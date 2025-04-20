package com.example.Hello.Service;


import com.example.Hello.Mapper.StoreBranchMapper;
import com.example.Hello.Repository.StoreBranchRepository;
import com.example.Hello.Repository.StoreRepository;
import com.example.Hello.dto.StoreBranchDTO;
import com.example.Hello.entity.StoreBranch;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StoreBranchService {

    private final StoreBranchRepository storeBranchRepository;
    private final StoreRepository storeRepository;
    private final StoreBranchMapper storeBranchMapper;

    public StoreBranchDTO createStoreBranch(StoreBranchDTO storeBranchDTO) {
        StoreBranch storeBranch = storeBranchMapper.toEntity(storeBranchDTO);
        storeBranch.setStore(storeRepository.findById(storeBranchDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        storeBranch.setCreatedAt(LocalDateTime.now());
        storeBranch.setUpdatedAt(LocalDateTime.now());
        return storeBranchMapper.toDTO(storeBranchRepository.save(storeBranch));
    }

    public StoreBranchDTO updateStoreBranch(Long id, StoreBranchDTO storeBranchDTO) {
        StoreBranch storeBranch = storeBranchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StoreBranch not found"));
        storeBranch.setStore(storeRepository.findById(storeBranchDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        storeBranch.setBranchName(storeBranchDTO.getBranchName());
        storeBranch.setAddress(storeBranchDTO.getAddress());
        storeBranch.setPhoneNumber(storeBranchDTO.getPhoneNumber());
        storeBranch.setUpdatedAt(LocalDateTime.now());
        return storeBranchMapper.toDTO(storeBranchRepository.save(storeBranch));
    }

    public void deleteStoreBranch(Long id) {
        storeBranchRepository.deleteById(id);
    }

    public StoreBranchDTO getStoreBranchById(Long id) {
        return storeBranchMapper.toDTO(storeBranchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StoreBranch not found")));
    }

    public Page<StoreBranchDTO> getStoreBranchesByStore(Long storeId, Pageable pageable) {
        return storeBranchRepository.findByStoreStoreId(storeId, pageable).map(storeBranchMapper::toDTO);
    }
}

