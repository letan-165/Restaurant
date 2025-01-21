package com.example.Invoice.Controller;

import com.example.Invoice.DTO.ApiResponse;
import com.example.Invoice.DTO.Request.ListTableRequest;
import com.example.Invoice.Service.InvoiceTableService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/invoice/table")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceTableController {
    InvoiceTableService invoiceTableService;

    @GetMapping("/{invoiceID}")
    ApiResponse<Set<String>> findAll(@PathVariable String invoiceID){
        return ApiResponse.<Set<String>>builder()
                .result(invoiceTableService.findAll(invoiceID))
                .build();
    }

    @PostMapping("/{invoiceID}/{tableID}")
    ApiResponse<Boolean> save(@PathVariable String invoiceID,@PathVariable String tableID){
        return ApiResponse.<Boolean>builder()
                .result(invoiceTableService.save(invoiceID,tableID))
                .build();
    }

    @PostMapping("/{invoiceID}")
    ApiResponse<Boolean> save(@PathVariable String invoiceID ,@RequestBody ListTableRequest tableIDs){
        return ApiResponse.<Boolean>builder()
                .result(invoiceTableService.save(invoiceID,tableIDs.getTableIDs()))
                .build();
    }

    @DeleteMapping("/{invoiceID}/{tableID}")
    ApiResponse<Boolean> delete(@PathVariable String invoiceID,@PathVariable String tableID){
        return ApiResponse.<Boolean>builder()
                .result(invoiceTableService.delete(invoiceID,tableID))
                .build();
    }

}
