package com.example.Booking.Controller;

import com.example.Booking.DTO.ApiResponse;
import com.example.Booking.DTO.Request.InvoiceUpdateRequest;
import com.example.Booking.DTO.Response.InvoiceFindByIdResponse;
import com.example.Booking.Entity.Invoice;
import com.example.Booking.Service.InvoiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class InvoiceController {
    InvoiceService invoiceService;

    @GetMapping
    ApiResponse<List<Invoice>>findAll(){
        return ApiResponse.<List<Invoice>>builder()
                .result(invoiceService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> save(){
        return ApiResponse.<Boolean>builder()
                .result(invoiceService.save())
                .build();
    }
    @DeleteMapping("/{invoiceID}")
    ApiResponse<Boolean> delete(@PathVariable String invoiceID){
        return ApiResponse.<Boolean>builder()
                .result(invoiceService.deleteById(invoiceID))
                .build();
    }

    @PostMapping("/{invoiceID}")
    ApiResponse<InvoiceFindByIdResponse> findById(@PathVariable String invoiceID){
        return ApiResponse.<InvoiceFindByIdResponse>builder()
                .result(invoiceService.findById(invoiceID))
                .build();
    }

    @PutMapping("/{invoiceID}")
    ApiResponse<Boolean> update(@PathVariable String invoiceID,@RequestBody InvoiceUpdateRequest request){
        return ApiResponse.<Boolean>builder()
                .result(invoiceService.update(invoiceID,request))
                .build();
    }

}
