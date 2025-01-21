package com.example.User.Mapper;

import com.example.User.DTO.Request.PermissionSaveRequest;
import com.example.User.DTO.Response.PermissionFindByIdResponse;
import com.example.User.Entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionSaveRequest request);
    PermissionFindByIdResponse toPermissionFindByIdRequest(Permission permission);

}

