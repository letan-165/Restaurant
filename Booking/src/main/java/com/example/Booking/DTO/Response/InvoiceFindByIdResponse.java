package com.example.Booking.DTO.Response;

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
public class InvoiceFindByIdResponse {
    String invoiceID;
    Set<String> tables;
    Set<OrderItem> orders;
    Long total;
    String state;
    LocalDateTime time;
}
