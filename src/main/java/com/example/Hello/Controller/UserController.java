package com.example.Hello.Controller;

import com.example.Hello.Service.UserService;
import com.example.Hello.dto.request.LoginRequest;
import com.example.Hello.dto.request.UserCreationRequest;
import com.example.Hello.dto.request.UserUpdataRequest;
import com.example.Hello.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping()
    User CreateUser (@RequestBody @Valid UserCreationRequest request){
        return userService.CreateUser(request);
    }
    @GetMapping()
    List<User> getUers (){
        return userService.getUsers();
    }
    @GetMapping("/{userId}")
    User getUser (@PathVariable String userId){
        return userService.getUser(userId);
    }

    @GetMapping("/username={username}")
    User getUserByUsername (@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{userId}")
    User update (@PathVariable String userId, @RequestBody @Valid UserUpdataRequest request){
        return  userService.updateUser(userId,request);
    }
    @DeleteMapping("/{userId}")
    String delete (@PathVariable String userId){
        userService.delateUser(userId);
        return "user has been delete";
    }
    @PostMapping("/login")
    public User login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
