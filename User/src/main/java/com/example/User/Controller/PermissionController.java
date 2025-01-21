package com.example.User.Controller;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.PermissionSaveRequest;
import com.example.User.DTO.Request.RoleUpdateRequest;
import com.example.User.DTO.Response.PermissionFindByIdResponse;
import com.example.User.Entity.Permission;
import com.example.User.Service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @GetMapping
    ApiResponse<List<Permission>> findAll(){
        return ApiResponse.<List<Permission>>builder()
                .result(permissionService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody PermissionSaveRequest request){
        return ApiResponse.<Boolean>builder()
                .result(permissionService.save(request))
                .build();
    }
    @DeleteMapping("/{permissionName}")
    ApiResponse<Boolean> deleteById(@PathVariable String permissionName){
        return ApiResponse.<Boolean>builder()
                .result(permissionService.deleteById(permissionName))
                .build();
    }

    @PutMapping("/{permissionName}")
    ApiResponse<Boolean> update(@PathVariable String permissionName, @RequestBody String description){
        return ApiResponse.<Boolean>builder()
                .result(permissionService.update(permissionName,description))
                .build();
    }
    @PostMapping("/{permissionName}")
    ApiResponse<PermissionFindByIdResponse> findById(@PathVariable String permissionName){
        return ApiResponse.<PermissionFindByIdResponse>builder()
                .result(permissionService.findById(permissionName))
                .build();
    }



}
