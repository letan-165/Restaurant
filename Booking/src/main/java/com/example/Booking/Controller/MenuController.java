package com.example.Booking.Controller;

import com.example.Booking.DTO.ApiResponse;
import com.example.Booking.DTO.Request.MenuSaveRequest;
import com.example.Booking.DTO.Request.MenuUpdateRequest;
import com.example.Booking.DTO.Response.MenuFindByIdResponse;
import com.example.Booking.Entity.Menu;
import com.example.Booking.Mapper.MenuMapper;
import com.example.Booking.Service.MenuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MenuController {
    MenuService menuService;
    MenuMapper menuMapper;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    ApiResponse<List<Menu>> findAll(){
        return ApiResponse.<List<Menu>>builder()
                .result(menuService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody MenuSaveRequest request){
        return ApiResponse.<Boolean>builder()
                .result(menuService.save(request))
                .build();
    }

    @DeleteMapping("/{itemID}")
    ApiResponse<Boolean> deleteById(@PathVariable String itemID){
        return ApiResponse.<Boolean>builder()
                .result(menuService.deleteById(itemID))
                .build();
    }

    @PostMapping("/{itemID}")
    ApiResponse<MenuFindByIdResponse> findById(@PathVariable String itemID){
        return ApiResponse.<MenuFindByIdResponse>builder()
                .result(menuService.findById(itemID))
                .build();
    }

    @PutMapping("/{itemID}")
    ApiResponse<Boolean> update(@PathVariable String itemID,@RequestBody MenuUpdateRequest request){
        MenuSaveRequest menuSaveRequest = menuMapper.toMenuSaveRequest(itemID, request);
        return ApiResponse.<Boolean>builder()
                .result(menuService.update(menuSaveRequest))
                .build();
    }

}
