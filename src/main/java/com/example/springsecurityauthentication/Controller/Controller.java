package com.example.springsecurityauthentication.Controller;

import com.example.springsecurityauthentication.Entity.UserInfo;
import com.example.springsecurityauthentication.Service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ss")
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(){
        return "this is Home Page";
    }

    @PostMapping("/new")
    public String addUser(@RequestBody UserInfo userInfo){
        userService.addUser(userInfo);
        return "user added";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "this is Admin Page";
    }
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String user(){
        return "this is User page";
    }

}
