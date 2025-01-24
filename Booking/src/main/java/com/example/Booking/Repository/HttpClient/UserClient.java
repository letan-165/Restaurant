package com.example.Booking.Repository.HttpClient;

import com.example.Booking.DTO.ApiResponse;
import com.example.Booking.DTO.Response.UserFindByIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service", url = "${app.service.user}")
public interface UserClient {
    @PostMapping(value = "/name={username}",  produces = MediaType.APPLICATION_JSON_VALUE)
    UserFindByIDResponse findByName(@PathVariable String username);

    @PostMapping(value = "/id={userID}",  produces = MediaType.APPLICATION_JSON_VALUE)
    UserFindByIDResponse findById(@PathVariable String userID);

}
