package com.example.blogging_api.service;

import com.example.blogging_api.dto.UserDto;
import com.example.blogging_api.entity.User;


public interface UserService {


    UserDto saveUser(User user);
}
