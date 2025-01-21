package com.example.Menu.Mapper;

import com.example.Menu.DTO.Request.MenuSaveRequest;
import com.example.Menu.DTO.Request.MenuUpdateRequest;
import com.example.Menu.DTO.Response.MenuFindByIdResponse;
import com.example.Menu.Entity.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    Menu toMenu(MenuSaveRequest request);
    Menu toMenu(MenuUpdateRequest request);

    MenuFindByIdResponse toMenuFindByIdRequest(Menu menu);
    MenuSaveRequest toMenuSaveRequest(String itemID, MenuUpdateRequest request);
}
