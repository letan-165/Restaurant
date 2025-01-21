package com.example.User.Service;


import com.example.User.DTO.Request.RoleSaveRequest;
import com.example.User.DTO.Request.RoleUpdateRequest;
import com.example.User.DTO.Response.RoleFindByIdResponse;
import com.example.User.Entity.Permission;
import com.example.User.Entity.Role;
import com.example.User.Exception.AppException;
import com.example.User.Exception.ErrolCode;
import com.example.User.Mapper.RoleMapper;
import com.example.User.Repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public boolean save(RoleSaveRequest request){
        if(roleRepository.existsById(request.getRoleName())){
            throw new AppException(ErrolCode.ROLE_NAME_EXITS);
        }
        roleRepository.save(roleMapper.toRole(request));
        return true;
    }

    public boolean deleteById(String roleName){
        if(!roleRepository.existsById(roleName)){
            throw new AppException(ErrolCode.ROLE_NAME_NO_EXITS);
        }
        roleRepository.deleteById(roleName);
        return true;
    }

    public boolean update(String roleName, RoleUpdateRequest request){
        if(!roleRepository.existsById(roleName)){
            throw new AppException(ErrolCode.ROLE_NAME_NO_EXITS);
        }
        Role role = new Role(roleName, request.getDescription(), request.getRole().getPermissions());
        roleRepository.save(role);
        return true;
    }

    public RoleFindByIdResponse findById(String roleName){
        if(!roleRepository.existsById(roleName)){
            throw new AppException(ErrolCode.ROLE_NAME_NO_EXITS);
        }
        return roleMapper.toRoleFindByIdRequest(roleRepository.findById(roleName)
                .orElseThrow(()->new AppException(ErrolCode.ROLE_NAME_NO_EXITS)));
    }
}
