package com.example.demo.Service;

import com.example.demo.DTO.FormLogin;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Request.RequestCreateUser;

import java.util.List;

public interface UserService {
    UserDTO getInfo();

    UserDTO getInfoId(Integer id);

    String createUser(RequestCreateUser rcu);

    String updateUser(Integer id,RequestCreateUser rcu);

    String deleteUser(Integer id);

    List<UserDTO> getUserByRole(String role);

    String login(FormLogin formLogin);

    List<UserDTO> getUserByField(RequestCreateUser rcu);
}
