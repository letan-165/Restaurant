package com.example.Logout.Mapper;

import com.example.Logout.DTO.Request.LogoutRequest;
import com.example.Logout.DTO.Response.LogoutRespone;
import com.example.Logout.Entity.Logout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogoutMapper {
    Logout toLogout(LogoutRequest request);
    LogoutRespone toLogoutResponse(Logout logout);
}
