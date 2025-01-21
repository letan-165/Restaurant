package com.example.Booking.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuFindByIdResponse {
    String itemID;
    Long priceBig;
    Long priceSmall;
    String type;
    String state;
    String describe;
}
