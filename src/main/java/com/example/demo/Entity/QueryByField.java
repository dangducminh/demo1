package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryByField {
    private String email;
    private String name;
    private String phone;
    private String address;
    private Role role;
}
