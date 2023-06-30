package com.example.springsecurityauthentication.Service;

import com.example.springsecurityauthentication.Entity.UserInfo;
import org.springframework.stereotype.Service;


public interface UserService {

    public String addUser(UserInfo userInfo);
}
