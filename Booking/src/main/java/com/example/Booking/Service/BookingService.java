package com.example.Booking.Service;

import com.example.Booking.DTO.Request.BookingSaveRequest;
import com.example.Booking.DTO.Request.BookingUpdateRequest;
import com.example.Booking.DTO.Request.ListOrderItemRequest;
import com.example.Booking.DTO.Response.BookingFindByIdResponse;
import com.example.Booking.DTO.Response.UserFindByIDResponse;
import com.example.Booking.Entity.Booking;
import com.example.Booking.Entity.OrderItem;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Mapper.BookingMapper;
import com.example.Booking.Repository.BookingRepository;
import com.example.Booking.Repository.HttpClient.MenuClient;
import com.example.Booking.Repository.HttpClient.UserClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserClient userClient;
    MenuClient menuClient;

    void checkMenu(Set<OrderItem> list){
        list.stream().forEach(orderItem -> {
            if(menuClient.findById(orderItem.getItemID()) ==null){
                throw new AppException(ErrolCode.MENU_NO_EXISTS);
            }
        });
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public boolean save(BookingSaveRequest request){
        UserFindByIDResponse user = userClient.findById(request.getUserID());
        if (user==null){
            throw new AppException(ErrolCode.USER_NO_EXISTS);
        }
        Booking booking = bookingMapper.toBooking(request);
        checkMenu(request.getOrders());
        bookingRepository.save(booking);
        return true;
    }

    public BookingFindByIdResponse findById(String bookingID){
        return bookingMapper.toBookingFindByIdResponse(bookingRepository.findById(bookingID)
                .orElseThrow(()->new AppException(ErrolCode.BOOKING_NO_EXITS)));
    }

    public boolean deleteById(String bookingID){
        if(!bookingRepository.existsById(bookingID)){
            throw new AppException(ErrolCode.BOOKING_NO_EXITS);
        }
        bookingRepository.deleteById(bookingID);
        return true;
    }

    public boolean update(String bookingID, BookingUpdateRequest request){
        if(!bookingRepository.existsById(bookingID)){
            throw new AppException(ErrolCode.BOOKING_NO_EXITS);
        }
        UserFindByIDResponse user = userClient.findById(request.getUserID());
        if (user==null){
            throw new AppException(ErrolCode.USER_NO_EXISTS);
        }
        Booking booking = bookingMapper.toBooking(bookingID,request);
        checkMenu(request.getOrders());
        bookingRepository.save(booking);
        return true;
    }
    public boolean updateMenu(String bookingID, ListOrderItemRequest request ){
        Booking booking = bookingRepository.findById(bookingID).orElseThrow(()->new AppException(ErrolCode.BOOKING_NO_EXITS));
        booking.setOrders(request.getOrders());
        bookingRepository.save(booking);
        return true;
    }





}
