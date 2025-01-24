package com.example.Booking.DTO.Request;


import com.example.Booking.DTO.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingSaveRequest {
    String userID;
    Set<OrderItem> orders;
    String phone;
    LocalDateTime time;
    String state;
    String location;
    String note;
}
