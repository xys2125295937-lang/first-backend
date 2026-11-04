package com.example.first_project.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Visitor") String name) {
        return "Hello," + name;
    }
    @GetMapping("/user")
    public UserResponse getUser(){
        return  new UserResponse(1L,"majiaqi");
    }
}
