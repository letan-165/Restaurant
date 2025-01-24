package com.example.User.Controller;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.LogoutRequest;
import com.example.User.Entity.Logout;
import com.example.User.Service.LogoutService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logouts")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class LogoutController {
    LogoutService logoutService;

    @GetMapping
    ApiResponse<List<Logout>> findAll(){
        return ApiResponse.<List<Logout>>builder()
                .result(logoutService.findAll())
                .build();
    }

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
