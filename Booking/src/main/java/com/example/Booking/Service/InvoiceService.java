package com.example.Booking.Service;

import com.example.Booking.DTO.Request.InvoiceUpdateRequest;
import com.example.Booking.DTO.Response.InvoiceFindByIdResponse;
import com.example.Booking.Entity.Invoice;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Mapper.InvoiceMapper;
import com.example.Booking.Repository.InvoiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceService {
    InvoiceRepository invoiceRepository;
    InvoiceMapper invoiceMapper;

    public List<Invoice> findAll(){
        return invoiceRepository.findAll();
    }

    public boolean save(){
        Invoice invoice = Invoice.builder()
                .tables(Collections.emptySet())
                .orders(Collections.emptySet())
                .state("UNPAID")
                .time(LocalDateTime.now())
                .total(0L)
                .build();
        try{
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public InvoiceFindByIdResponse findById(String invoiceID){
        return invoiceMapper.toInvoiceFindByIdResponse(invoiceRepository.findById(invoiceID)
                .orElseThrow(()->new AppException(ErrolCode.INVOICE_NO_EXISTS)));
    }

    public boolean deleteById(String invoiceID){
        if(!invoiceRepository.existsById(invoiceID)){
            throw new AppException(ErrolCode.INVOICE_NO_EXISTS);
        }
        invoiceRepository.deleteById(invoiceID);
        return true;
    }

    public boolean update(String invoiceID, InvoiceUpdateRequest request){

        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(()-> new AppException(ErrolCode.INVOICE_NO_EXISTS));
        invoice.setState(request.getState());
        try{
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            return false;
        }
    }





}
