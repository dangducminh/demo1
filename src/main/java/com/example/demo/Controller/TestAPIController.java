package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/testAPI")
public class TestAPIController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value="/language")
    public String translate(@RequestHeader("language") String local) {
        return messageSource.getMessage("firstWord",null, new Locale(local));

    }
}