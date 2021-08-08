package com.example.demo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUser {
    private String email;
    private String name;
    private String phone;
    private String address;
    private String password;
    private double mathScore;
    private double literatureScore;
    private double englishScore;
    private String role;
}