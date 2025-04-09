package com.example.blogging_api.controller;


import com.example.blogging_api.dto.ApiResponse;
import com.example.blogging_api.dto.UserDto;
import com.example.blogging_api.dto.UserLoginDto;
import com.example.blogging_api.dto.UserRegisterDto;
import com.example.blogging_api.entity.User;
import com.example.blogging_api.security.JwtUtil;
import com.example.blogging_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public UserAuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody UserRegisterDto userRegisterDto){

        UserDto userDto = userService.saveUser(new User(userRegisterDto.getUsername(),
                userRegisterDto.getPassword(),
                userRegisterDto.getEmail()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<UserDto>(true, "registration successful", userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginDto userLoginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        String token = jwtUtil.generateToken(userLoginDto.getUsername());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "request successful", token));
    }
}
