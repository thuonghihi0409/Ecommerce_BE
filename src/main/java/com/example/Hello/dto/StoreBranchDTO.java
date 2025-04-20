package com.example.Hello.dto;


import lombok.Data;

@Data
public class StoreBranchDTO {
    private Long branchId;
    private Long storeId;
    private String branchName;
    private String address;
    private String phoneNumber;
}
