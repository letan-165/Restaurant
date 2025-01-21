package com.example.User.Repository.HttpClient;

import com.example.User.Config.AuthenticationRequestInterceptor;
import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.ProfileRequest;
import com.example.User.DTO.Response.ProfileFindByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="profile-service", url = "${app.service.profile}",configuration = AuthenticationRequestInterceptor.class)
public interface ProfileClient {

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody ProfileRequest request);

    @PutMapping
    ApiResponse<Boolean> update(@RequestBody ProfileRequest request);

    @PostMapping("/{userID}")
    ApiResponse<ProfileFindByIdResponse> findById(@PathVariable String userID);

    @DeleteMapping("/{userID}")
    ApiResponse<Boolean>deleteById(@PathVariable String userID);
}
