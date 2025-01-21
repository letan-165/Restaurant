package com.example.Booking.DTO.Request;

import com.example.Booking.Entity.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListOrderItemRequest {
    Set<OrderItem> orders;
}
