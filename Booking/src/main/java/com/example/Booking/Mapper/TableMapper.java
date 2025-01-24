package com.example.Booking.Mapper;

import com.example.Booking.DTO.Request.TableSaveRequest;
import com.example.Booking.DTO.Response.TableFindByIdResponse;
import com.example.Booking.DTO.Response.TableUpdateResponse;
import com.example.Booking.Entity.Table;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    Table toTable(TableSaveRequest request);
    TableFindByIdResponse toTableFindByIdResponse(Table table);
    Table toTable(String tableID, TableUpdateResponse response);

}
