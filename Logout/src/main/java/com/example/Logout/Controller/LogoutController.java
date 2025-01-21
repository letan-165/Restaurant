package com.example.Logout.Controller;

import com.example.Logout.DTO.ApiResponse;
import com.example.Logout.DTO.Request.ExpiryTimeRequest;
import com.example.Logout.DTO.Request.LogoutRequest;
import com.example.Logout.DTO.Response.LogoutRespone;
import com.example.Logout.Entity.Logout;
import com.example.Logout.Service.LogoutService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logout")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class LogoutController {
    LogoutService logoutService;

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody LogoutRequest request){
        return ApiResponse.<Boolean>builder()
                .result(logoutService.save(request))
                .build();
    }

    @PostMapping("/{token}")
    ApiResponse<Boolean> existsById(@PathVariable String token){
        return ApiResponse.<Boolean>builder()
                .result(logoutService.existsById(token))
                .build();
    }
}
