package com.example.Menu.DTO.Request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuSaveRequest {
    String itemID;
    Long priceBig;
    Long priceSmall;
    String type;
    String state;
    String describe;
}
