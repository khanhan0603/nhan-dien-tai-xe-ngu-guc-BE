package com.example.taixenguguc.service;

import com.example.taixenguguc.dbo.request.UserCreationRequest;
import com.example.taixenguguc.dbo.response.UserResponse;
import com.example.taixenguguc.entity.User;
import com.example.taixenguguc.exception.AppException;
import com.example.taixenguguc.exception.ErrorCode;
import com.example.taixenguguc.mapper.UserMapper;
import com.example.taixenguguc.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request){
        if(userRepository.existsByPhone(request.getPhone()))
            throw new AppException(ErrorCode.PHONE_EXISTED);

        User user=userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
