package com.example.Booking.Mapper;

import com.example.Booking.DTO.Request.MenuSaveRequest;
import com.example.Booking.DTO.Request.MenuUpdateRequest;
import com.example.Booking.DTO.Response.MenuFindByIdResponse;
import com.example.Booking.Entity.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    Menu toMenu(MenuSaveRequest request);
    Menu toMenu(MenuUpdateRequest request);

    MenuFindByIdResponse toMenuFindByIdRequest(Menu menu);
    MenuSaveRequest toMenuSaveRequest(String itemID, MenuUpdateRequest request);
}
