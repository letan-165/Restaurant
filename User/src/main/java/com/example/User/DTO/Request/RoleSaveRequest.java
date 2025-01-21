package com.example.User.DTO.Request;


import com.example.User.Entity.Permission;
import com.example.User.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleSaveRequest {
    String roleName;
    String description;
    List<Permission> permissions;
}
