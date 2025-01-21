package com.example.Invoice.Mapper;

import com.example.Invoice.DTO.Request.InvoiceUpdateRequest;
import com.example.Invoice.DTO.Response.InvoiceFindByIdResponse;
import com.example.Invoice.Entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(String invoiceID,InvoiceUpdateRequest request);

    InvoiceFindByIdResponse toInvoiceFindByIdResponse(Invoice invoice);

}
