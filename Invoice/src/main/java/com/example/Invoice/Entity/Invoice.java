package com.example.Invoice.Entity;

import com.example.Invoice.Entity.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "invoice-service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {

    @Id
    String invoiceID;
    Set<String> tables;
    Set<OrderItem> orders;
    Long total;
    String state;
    LocalDateTime time;

}