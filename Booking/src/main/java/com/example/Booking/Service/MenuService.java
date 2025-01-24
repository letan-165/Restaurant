package com.example.Booking.Service;

import com.example.Booking.DTO.Request.MenuSaveRequest;
import com.example.Booking.DTO.Response.MenuFindByIdResponse;
import com.example.Booking.Entity.Menu;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Mapper.MenuMapper;
import com.example.Booking.Repository.MenuRepository;
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
            throw new AppException(ErrolCode.ERROL_OTHER);
        }
        menuRepository.save(menuMapper.toMenu(request));
        return true;
    }

    public boolean deleteById(String itemID) {
        if (!menuRepository.existsById(itemID)) {
            throw new AppException(ErrolCode.ERROL_OTHER);
        }
        menuRepository.deleteById(itemID);
        return true;
    }

    public MenuFindByIdResponse findById(String itemID) {
        return menuMapper.toMenuFindByIdRequest(menuRepository.findById(itemID)
                .orElseThrow(()->new AppException(ErrolCode.ERROL_OTHER)));
    }

    public boolean update(MenuSaveRequest request) {
        if (!menuRepository.existsById(request.getItemID())) {
            throw new AppException(ErrolCode.ERROL_OTHER);
        }
        menuRepository.save(menuMapper.toMenu(request));
        return true;
    }
}
