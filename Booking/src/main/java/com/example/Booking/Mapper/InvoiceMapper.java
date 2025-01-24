package com.example.Booking.Mapper;

import com.example.Booking.DTO.Request.InvoiceUpdateRequest;
import com.example.Booking.DTO.Response.InvoiceFindByIdResponse;
import com.example.Booking.Entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(String invoiceID,InvoiceUpdateRequest request);

    InvoiceFindByIdResponse toInvoiceFindByIdResponse(Invoice invoice);

}
