package com.example.first_project.start;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Visitor") String name) {
        return "Hello," + name;
    }


    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable("id") Long id){
        return  userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers(@RequestParam(value = "name", required = false) String name){
        return userService.getUsers(name);
    }

    @PostMapping("/users/preview")
    public UserResponse previewUser(
            @RequestBody CreateUserRequest request) {

        return new UserResponse(0L, request.getName());
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody CreateUserRequest request) {

        UserResponse user = userService.createUser(request.getName());

        return ResponseEntity.status(201).body(user);
    }
}
