package com.example.demo.Controller;

import com.example.demo.DTO.FormLogin;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication")
public class UserAuthentication {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> Login(@RequestBody FormLogin formLogin){
        return new ResponseEntity<>(userService.login(formLogin), HttpStatus.OK);
    }

}
