package com.example.Hello.Service;


import com.example.Hello.Mapper.LivestreamMapper;
import com.example.Hello.Repository.LivestreamRepository;
import com.example.Hello.Repository.StoreRepository;
import com.example.Hello.dto.LivestreamDTO;
import com.example.Hello.entity.Livestream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LivestreamService {

    private final LivestreamRepository livestreamRepository;
    private final StoreRepository storeRepository;
    private final LivestreamMapper livestreamMapper;

    public LivestreamDTO createLivestream(LivestreamDTO livestreamDTO) {
        Livestream livestream = livestreamMapper.toEntity(livestreamDTO);
        livestream.setStore(storeRepository.findById(livestreamDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        livestream.setCreatedAt(LocalDateTime.now());
        livestream.setUpdatedAt(LocalDateTime.now());
        return livestreamMapper.toDTO(livestreamRepository.save(livestream));
    }

    public LivestreamDTO updateLivestream(Long id, LivestreamDTO livestreamDTO) {
        Livestream livestream = livestreamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livestream not found"));
        livestream.setStore(storeRepository.findById(livestreamDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        livestream.setTitle(livestreamDTO.getTitle());
        livestream.setStreamUrl(livestreamDTO.getStreamUrl());
        livestream.setStartTime(livestreamDTO.getStartTime());
        livestream.setEndTime(livestreamDTO.getEndTime());
        livestream.setUpdatedAt(LocalDateTime.now());
        return livestreamMapper.toDTO(livestreamRepository.save(livestream));
    }

    public void deleteLivestream(Long id) {
        livestreamRepository.deleteById(id);
    }

    public LivestreamDTO getLivestreamById(Long id) {
        return livestreamMapper.toDTO(livestreamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livestream not found")));
    }

    public Page<LivestreamDTO> getLivestreamsByStore(Long storeId, Pageable pageable) {
        return livestreamRepository.findByStoreStoreId(storeId, pageable).map(livestreamMapper::toDTO);
    }
}
