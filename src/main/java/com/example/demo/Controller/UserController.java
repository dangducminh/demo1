package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Request.RequestCreateUser;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("info")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/findUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getInfoById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(userService.getInfoId(id), HttpStatus.OK);
    }

    @GetMapping(value="/findByRole")
    public ResponseEntity<List<UserDTO>> getUserByRole(@RequestParam(name = "role") String role){
        return new ResponseEntity<>(userService.getUserByRole(role), HttpStatus.OK);
    }

    @PostMapping(value = "/findByField")
    public ResponseEntity<List<UserDTO>>getUserByField(@RequestBody RequestCreateUser rcu){
        return new ResponseEntity<>(userService.getUserByField(rcu),HttpStatus.OK);
    }

    @GetMapping(value="/find")
    public UserDTO getInfo(){
        return userService.getInfo();
    }

    @PostMapping(value="/create")
    public String createUser(@RequestBody RequestCreateUser rcu){
        return userService.createUser(rcu);
    }

    @PostMapping(value="/update/{id}")
    public String updateUser (@PathVariable(name="id") Integer id,@RequestBody RequestCreateUser rcu){
        return userService.updateUser(id,rcu);
    }

    @DeleteMapping(value="/delete/{id}")
    public String deleteUser(@PathVariable(name ="id") Integer id) {
        return userService.deleteUser(id);
    }

}
