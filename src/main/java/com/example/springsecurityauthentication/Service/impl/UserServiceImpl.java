package com.example.springsecurityauthentication.Service.impl;

import com.example.springsecurityauthentication.Entity.UserInfo;
import com.example.springsecurityauthentication.Repository.UserInfoRepository;
import com.example.springsecurityauthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return null;
    }
}
