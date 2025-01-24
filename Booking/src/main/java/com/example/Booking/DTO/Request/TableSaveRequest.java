package com.example.Booking.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableSaveRequest {
    String tableID;
    String location;
    Long numPeople;
    String state;
}
