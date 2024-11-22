package com.example.Hello.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserUpdataRequest {
    @Size(min =8, message ="password it nhat 8 ky tu" )
    private String password;
    private String name;
    private String sdt;
    private String gmail;
    private  String vaitro;
    private String avturl ;
}