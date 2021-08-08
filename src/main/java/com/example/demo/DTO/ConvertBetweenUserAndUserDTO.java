package com.example.demo.DTO;

import com.example.demo.Entity.User;

public class ConvertBetweenUserAndUserDTO {
    public static UserDTO ConvertUserToUserDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .mathScore(user.getMathScore())
                .literatureScore(user.getLiteratureScore())
                .englishScore(user.getEnglishScore())
                .averageScore(user.getAverageScore())
                .role(user.getRole()).build();
    }
    public static User ConvertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .mathScore(userDTO.getMathScore())
                .literatureScore(userDTO.getLiteratureScore())
                .englishScore(userDTO.getEnglishScore())
                .averageScore(userDTO.getAverageScore())
                .role(userDTO.getRole()).build();
    }

}
