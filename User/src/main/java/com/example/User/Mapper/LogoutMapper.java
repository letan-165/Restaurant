package com.example.User.Mapper;


import com.example.User.DTO.Request.LogoutRequest;
import com.example.User.DTO.Response.LogoutRespone;
import com.example.User.Entity.Logout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogoutMapper {
    Logout toLogout(LogoutRequest request);
    LogoutRespone toLogoutResponse(Logout logout);
}
