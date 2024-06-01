package com.ecomart.datas.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String accountType;
    private int age;
}
