package com.example.User.Repository.HttpClient;

import com.example.User.DTO.ApiResponse;
import com.example.User.DTO.Request.LogoutRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logout-service", url = "${app.service.logout}")
public interface LogoutClient {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Boolean> save(@RequestBody LogoutRequest request);

    @PostMapping(value = "/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Boolean> existsById(@PathVariable String token);

}
