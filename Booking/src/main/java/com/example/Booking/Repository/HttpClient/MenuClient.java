package com.example.Booking.Repository.HttpClient;

import com.example.Booking.DTO.Response.MenuFindByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "menu-client", url = "${app.service.menu}")
public interface MenuClient {

    @PostMapping(value = "/{menuId}",produces = MediaType.APPLICATION_JSON_VALUE)
    MenuFindByIdResponse findById(@PathVariable String menuId);

}
