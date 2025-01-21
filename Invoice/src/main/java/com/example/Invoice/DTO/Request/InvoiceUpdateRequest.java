package com.example.Invoice.DTO.Request;

import com.example.Invoice.Entity.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceUpdateRequest {
    String state;
}
