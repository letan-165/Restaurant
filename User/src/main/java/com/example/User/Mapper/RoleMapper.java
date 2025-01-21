package com.example.User.Mapper;

import com.example.User.DTO.Request.RoleSaveRequest;
import com.example.User.DTO.Response.RoleFindByIdResponse;
import com.example.User.Entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleSaveRequest request);
    RoleFindByIdResponse toRoleFindByIdRequest(Role role);

}

