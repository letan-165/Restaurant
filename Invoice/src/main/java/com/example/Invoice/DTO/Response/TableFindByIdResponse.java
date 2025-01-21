package com.example.Invoice.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableFindByIdResponse {
    String tableID;
    String location;
    Long numPeople;
    String state;
}
