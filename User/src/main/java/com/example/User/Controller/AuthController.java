package com.example.User.Controller;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.TokenRequest;
import com.example.User.DTO.Request.LoginRequest;
import com.example.User.DTO.Response.UserFindByIDResponse;
import com.example.User.Service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthController {
    AuthService authService;
    @PostMapping("/login")
    ApiResponse<String> login(@RequestBody LoginRequest request){
        return ApiResponse.<String>builder()
                .result(authService.login(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<Boolean>introspect(@RequestBody TokenRequest request){
        return ApiResponse.<Boolean>builder()
                .result(authService.introspect(request))
                .build();
    }
    @GetMapping("/info")
    ApiResponse<String>info(){
        return ApiResponse.<String>builder()
                .result(authService.info())
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Boolean>logout(@RequestBody TokenRequest request){
        return ApiResponse.<Boolean>builder()
                .result(authService.logout(request))
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<String>refresh(@RequestBody TokenRequest request){
        return ApiResponse.<String>builder()
                .result(authService.refresh(request))
                .build();
    }

}
