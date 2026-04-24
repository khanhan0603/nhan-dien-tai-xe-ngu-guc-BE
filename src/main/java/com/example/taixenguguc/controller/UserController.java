package com.example.taixenguguc.controller;

import com.example.taixenguguc.dbo.request.UserCreationRequest;
import com.example.taixenguguc.dbo.response.ApiResponse;
import com.example.taixenguguc.dbo.response.UserResponse;
import com.example.taixenguguc.entity.User;
import com.example.taixenguguc.service.AuthService;
import com.example.taixenguguc.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;
     AuthService authService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse=new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping("/me")
    public UserResponse getMyInfo(@RequestHeader("Authorization") String authHeader) {

        // format: Bearer token
        String token = authHeader.replace("Bearer ", "");

        String userId = authService.extractUserId(token);

        return userService.getUser(userId);
    }
}
