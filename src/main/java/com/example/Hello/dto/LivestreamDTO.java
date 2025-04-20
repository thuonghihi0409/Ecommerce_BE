package com.example.Hello.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LivestreamDTO {
    private Long livestreamId;
    private Long storeId;
    private String title;
    private String streamUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
