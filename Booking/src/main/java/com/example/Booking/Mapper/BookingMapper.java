package com.example.Booking.Mapper;

import com.example.Booking.DTO.Request.BookingSaveRequest;
import com.example.Booking.DTO.Request.BookingUpdateRequest;
import com.example.Booking.DTO.Response.BookingFindByIdResponse;
import com.example.Booking.Entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingSaveRequest request);
    Booking toBooking(String bookingID,BookingUpdateRequest request);

    BookingFindByIdResponse toBookingFindByIdResponse(Booking booking);

}
