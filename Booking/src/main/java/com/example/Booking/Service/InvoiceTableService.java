package com.example.Booking.Service;

import com.example.Booking.Entity.Invoice;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Repository.InvoiceRepository;
import com.example.Booking.Repository.TableRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceTableService {
    InvoiceRepository invoiceRepository;
    TableRepository tableRepository;

    Invoice findById(String invoiceID){
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(()->new AppException(ErrolCode.INVOICE_NO_EXISTS));
    }
    //Trả về true nếu ko có table
    boolean checkTableID(String tableID){
        return tableRepository.findById(tableID) == null;
    }

    public Set<String> findAll(String invoiceID){
        Invoice invoice = findById(invoiceID);
        return invoice.getTables();
    }

    //Save base on (InvoiceID and tableID)
    public boolean save(String invoiceID,String tableID){
        Invoice invoice = findById(invoiceID);

        if(invoice.getTables().contains(tableID) || checkTableID(tableID)){
            return false;
        }
        invoice.getTables().add(tableID);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Save base on (InvoiceID and list table)
    public boolean save(String invoiceID, Set<String> tables){
        Invoice invoice = findById(invoiceID);
        invoice.setTables(tables);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String invoiceID,String tableID){
        Invoice invoice = findById(invoiceID);
        if(!invoice.getTables().contains(tableID)){
            return false;
        }
        invoice.getTables().remove(tableID);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
