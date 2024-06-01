package com.ecomart.datas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Setter @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class AppUser {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Role role;
    private int age;
}
