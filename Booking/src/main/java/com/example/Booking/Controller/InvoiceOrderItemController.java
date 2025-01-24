package com.example.Booking.Controller;

import com.example.Booking.DTO.ApiResponse;
import com.example.Booking.DTO.Request.ListOrderItemRequest;
import com.example.Booking.DTO.OrderItem;
import com.example.Booking.Service.InvoiceOrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/invoice/orderItem")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceOrderItemController {
    InvoiceOrderItemService invoiceOrderItemService;

    @GetMapping("/{invoiceID}")
    ApiResponse<Set<OrderItem>> findAll(@PathVariable String invoiceID){
        return ApiResponse.<Set<OrderItem>>builder()
                .result(invoiceOrderItemService.findAll(invoiceID))
                .build();
    }

    @PostMapping("/orderItem-{invoiceID}")
    ApiResponse<Boolean> save(@PathVariable String invoiceID,@RequestBody OrderItem orderItem){
        return ApiResponse.<Boolean>builder()
                .result(invoiceOrderItemService.save(invoiceID,orderItem))
                .build();
    }

    @PostMapping({"/orderItems-{invoiceID}"})
    ApiResponse<Boolean> save(@PathVariable String invoiceID, @RequestBody ListOrderItemRequest orderItems){
        return ApiResponse.<Boolean>builder()
                .result(invoiceOrderItemService.save(invoiceID,orderItems.getOrderItems()))
                .build();
    }


    @DeleteMapping({"/{invoiceID}/{tableID}"})
    ApiResponse<Boolean> delete(@PathVariable String invoiceID,@PathVariable String tableID){
        return ApiResponse.<Boolean>builder()
                .result(invoiceOrderItemService.delete(invoiceID,tableID))
                .build();
    }
}
