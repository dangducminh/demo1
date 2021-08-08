package com.example.demo.DTO;

import com.example.demo.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String phone;
    private String address;
    private Double mathScore;
    private Double literatureScore;
    private Double englishScore;
    private Double averageScore;
    private Role role;
}
