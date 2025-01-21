package com.example.Booking.Controller;

import com.example.Booking.DTO.ApiResponse;
import com.example.Booking.DTO.Request.BookingSaveRequest;
import com.example.Booking.DTO.Request.BookingUpdateRequest;
import com.example.Booking.DTO.Request.ListOrderItemRequest;
import com.example.Booking.DTO.Response.BookingFindByIdResponse;
import com.example.Booking.Entity.Booking;
import com.example.Booking.Service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class BookingController {
    BookingService bookingService;

    @GetMapping
    ApiResponse<List<Booking>>findAll(){
        return ApiResponse.<List<Booking>>builder()
                .result(bookingService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody BookingSaveRequest request){
        return ApiResponse.<Boolean>builder()
                .result(bookingService.save(request))
                .build();
    }
    @DeleteMapping("/{bookingID}")
    ApiResponse<Boolean> delete(@PathVariable String bookingID){
        return ApiResponse.<Boolean>builder()
                .result(bookingService.deleteById(bookingID))
                .build();
    }

    @PostMapping("/{bookingID}")
    ApiResponse<BookingFindByIdResponse> findById(@PathVariable String bookingID){
        return ApiResponse.<BookingFindByIdResponse>builder()
                .result(bookingService.findById(bookingID))
                .build();
    }

    @PutMapping("/{bookingID}")
    ApiResponse<Boolean> update(@PathVariable String bookingID,@RequestBody BookingUpdateRequest request){
        return ApiResponse.<Boolean>builder()
                .result(bookingService.update(bookingID,request))
                .build();
    }
    @PostMapping("/menu/{bookingID}")
    ApiResponse<Boolean> updateMenu(@PathVariable String bookingID,@RequestBody ListOrderItemRequest request){
        return ApiResponse.<Boolean>builder()
                .result(bookingService.updateMenu(bookingID,request))
                .build();
    }

}
