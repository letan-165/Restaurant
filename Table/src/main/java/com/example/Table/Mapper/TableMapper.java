package com.example.Table.Mapper;

import com.example.Table.DTO.Request.TableSaveRequest;
import com.example.Table.DTO.Response.TableFindByIdResponse;
import com.example.Table.DTO.Response.TableUpdateResponse;
import com.example.Table.Entity.Table;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    Table toTable(TableSaveRequest request);
    TableFindByIdResponse toTableFindByIdResponse(Table table);
    Table toTable(String tableID, TableUpdateResponse response);

}
