package com.example.User.Service;


import com.example.User.DTO.Request.PermissionSaveRequest;
import com.example.User.DTO.Request.RoleUpdateRequest;
import com.example.User.DTO.Response.PermissionFindByIdResponse;
import com.example.User.Entity.Permission;
import com.example.User.Entity.Permission;
import com.example.User.Exception.AppException;
import com.example.User.Exception.ErrolCode;
import com.example.User.Mapper.PermissionMapper;
import com.example.User.Repository.PermissionRepository;
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
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<Permission> findAll(){
        return permissionRepository.findAll();
    }

    public boolean save(PermissionSaveRequest request){
        if(permissionRepository.existsById(request.getPermissionName())){
            throw new AppException(ErrolCode.PERMISSION_NAME_EXITS);
        }
        permissionRepository.save(permissionMapper.toPermission(request));
        return true;
    }

    public boolean deleteById(String permissionName){
        if(!permissionRepository.existsById(permissionName)){
            throw new AppException(ErrolCode.PERMISSION_NAME_NO_EXITS);
        }
        permissionRepository.deleteById(permissionName);
        return true;
    }

    public boolean update(String permissionName, String description){
        if(!permissionRepository.existsById(permissionName)){
            throw new AppException(ErrolCode.PERMISSION_NAME_NO_EXITS);
        }
        Permission permission = new Permission(permissionName, description);
        permissionRepository.save(permission);
        return true;
    }

    public PermissionFindByIdResponse findById(String permissionName){
        if(!permissionRepository.existsById(permissionName)){
            throw new AppException(ErrolCode.PERMISSION_NAME_NO_EXITS);
        }
        return permissionMapper.toPermissionFindByIdRequest(permissionRepository.findById(permissionName)
                .orElseThrow(()->new AppException(ErrolCode.PERMISSION_NAME_NO_EXITS)));
    }
}
