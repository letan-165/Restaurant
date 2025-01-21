package com.example.User.Controller;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.UserSaveRequest;
import com.example.User.DTO.Request.UserUpdateRequest;
import com.example.User.DTO.Response.UserFindByIDResponse;
import com.example.User.DTO.Response.UserSaveResponse;
import com.example.User.Entity.User;
import com.example.User.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<List<User>> findAll(){
        return ApiResponse.<List<User>>builder()
                .result(userService.findAll())
                .build();
    }

    @PostMapping("/public")
    ApiResponse<UserSaveResponse> save(@RequestBody UserSaveRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return ApiResponse.<UserSaveResponse>builder()
                .result(userService.save(request))
                .build();
    }
    @PutMapping("/public/{userID}")
    ApiResponse<UserSaveResponse> update(@PathVariable String userID,@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserSaveResponse>builder()
                .result(userService.update(userID,request))
                .build();
    }

    @PostMapping("/name={username}")
    ApiResponse<UserFindByIDResponse>findByName(@PathVariable String username){
        return ApiResponse.<UserFindByIDResponse>builder()
                .result(userService.findByName(username))
                .build();
    }

    @PostMapping("/id={userID}")
    ApiResponse<UserFindByIDResponse>findById(@PathVariable String userID){
        return ApiResponse.<UserFindByIDResponse>builder()
                .result(userService.findById(userID))
                .build();
    }
    
    @DeleteMapping("/{userID}")
    ApiResponse<Boolean> delete(@PathVariable String userID){
        return ApiResponse.<Boolean>builder()
                .result(userService.delete(userID))
                .build();
    }

}
