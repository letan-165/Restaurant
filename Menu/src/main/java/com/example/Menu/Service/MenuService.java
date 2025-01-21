package com.example.Menu.Service;

import com.example.Menu.DTO.Request.MenuSaveRequest;
import com.example.Menu.DTO.Response.MenuFindByIdResponse;
import com.example.Menu.Entity.Menu;
import com.example.Menu.Exception.AppException;
import com.example.Menu.Exception.ErrolCode;
import com.example.Menu.Mapper.MenuMapper;
import com.example.Menu.Repository.MenuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MenuService {
    MenuRepository menuRepository;
    MenuMapper menuMapper;

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public boolean save(MenuSaveRequest request) {
        if (menuRepository.existsById(request.getItemID())) {
            throw new AppException(ErrolCode.NAME_ITEM_EXITS);
        }
        menuRepository.save(menuMapper.toMenu(request));
        return true;
    }

    public boolean deleteById(String itemID) {
        if (!menuRepository.existsById(itemID)) {
            throw new AppException(ErrolCode.NAME_ITEM_NO_EXITS);
        }
        menuRepository.deleteById(itemID);
        return true;
    }

    public MenuFindByIdResponse findById(String itemID) {
        return menuMapper.toMenuFindByIdRequest(menuRepository.findById(itemID)
                .orElseThrow(()->new AppException(ErrolCode.NAME_ITEM_NO_EXITS)));
    }

    public boolean update(MenuSaveRequest request) {
        if (!menuRepository.existsById(request.getItemID())) {
            throw new AppException(ErrolCode.NAME_ITEM_NO_EXITS);
        }
        menuRepository.save(menuMapper.toMenu(request));
        return true;
    }
}
