package com.example.User.Mapper;

import com.example.User.DTO.Request.LoginRequest;
import com.example.User.DTO.Request.UserSaveRequest;
import com.example.User.DTO.Request.UserUpdateRequest;
import com.example.User.DTO.Response.UserFindByIDResponse;
import com.example.User.DTO.Response.UserSaveResponse;
import com.example.User.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(UserSaveRequest request);
    @Mapping(target = "role", ignore = true)
    User toUser(UserUpdateRequest request);
    User toUser(LoginRequest request);
    User toUser(UserFindByIDResponse request);

    UserSaveResponse toUserSaveResponse(User user);
    UserFindByIDResponse toUserFindByIDResponse(User user);
}
