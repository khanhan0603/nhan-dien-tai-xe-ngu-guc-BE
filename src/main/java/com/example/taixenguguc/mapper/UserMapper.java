package com.example.taixenguguc.mapper;


import com.example.taixenguguc.dbo.request.UserCreationRequest;
import com.example.taixenguguc.dbo.response.UserResponse;
import com.example.taixenguguc.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
}
