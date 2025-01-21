package com.example.Table.Controller;

import com.example.Table.DTO.ApiResponse;
import com.example.Table.DTO.Request.TableSaveRequest;
import com.example.Table.DTO.Response.TableFindByIdResponse;
import com.example.Table.DTO.Response.TableUpdateResponse;
import com.example.Table.Entity.Table;
import com.example.Table.Service.TableService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class TableController {
    TableService tableService;

    @GetMapping
    ApiResponse<List<Table>>findAll(){
        return ApiResponse.<List<Table>>builder()
                .result(tableService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(@RequestBody TableSaveRequest request){
        return ApiResponse.<Boolean>builder()
                .result(tableService.save(request))
                .build();
    }

    @PostMapping("/{tableID}")
    ApiResponse<TableFindByIdResponse> findById(@PathVariable String tableID){
        return ApiResponse.<TableFindByIdResponse>builder()
                .result(tableService.findById(tableID))
                .build();
    }

    @DeleteMapping("/{tableID}")
    ApiResponse<Boolean> deleteById(@PathVariable String tableID){
        return ApiResponse.<Boolean>builder()
                .result(tableService.deleteById(tableID))
                .build();
    }
    @PutMapping("/{tableID}")
    ApiResponse<Boolean> update(@PathVariable String tableID, @RequestBody TableUpdateResponse response){
        return ApiResponse.<Boolean>builder()
                .result(tableService.update(tableID,response))
                .build();
    }


}
