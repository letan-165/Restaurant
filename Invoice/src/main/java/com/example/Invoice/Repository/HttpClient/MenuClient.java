package com.example.Invoice.Repository.HttpClient;

import com.example.Invoice.DTO.ApiResponse;
import com.example.Invoice.DTO.Response.MenuFindByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "menu-client", url = "${app.service.menu}")
public interface MenuClient {

    @PostMapping(value = "/{menuId}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse <MenuFindByIdResponse> findById(@PathVariable String menuId);

}
