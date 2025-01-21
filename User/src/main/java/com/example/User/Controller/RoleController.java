package com.example.User.Controller;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.RoleSaveRequest;
import com.example.User.DTO.Request.RoleUpdateRequest;
import com.example.User.DTO.Response.RoleFindByIdResponse;
import com.example.User.Entity.Role;
import com.example.User.Service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping
    ApiResponse<List<Role>> findAll(){
        return ApiResponse.<List<Role>>builder()
                .result(roleService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody RoleSaveRequest request){
        return ApiResponse.<Boolean>builder()
                .result(roleService.save(request))
                .build();
    }
    @DeleteMapping("/{roleName}")
    ApiResponse<Boolean> deleteById(@PathVariable String roleName){
        return ApiResponse.<Boolean>builder()
                .result(roleService.deleteById(roleName))
                .build();
    }

    @PutMapping("/{roleName}")
    ApiResponse<Boolean> update(@PathVariable String roleName,@RequestBody RoleUpdateRequest request){
        return ApiResponse.<Boolean>builder()
                .result(roleService.update(roleName,request))
                .build();
    }
    @PostMapping("/{roleName}")
    ApiResponse<RoleFindByIdResponse> findById(@PathVariable String roleName){
        return ApiResponse.<RoleFindByIdResponse>builder()
                .result(roleService.findById(roleName))
                .build();
    }



}
