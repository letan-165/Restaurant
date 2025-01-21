package com.example.Invoice.Service;

import com.example.Invoice.Entity.Invoice;
import com.example.Invoice.Exception.AppException;
import com.example.Invoice.Exception.ErrolCode;
import com.example.Invoice.Mapper.InvoiceMapper;
import com.example.Invoice.Repository.HttpClient.TableClient;
import com.example.Invoice.Repository.InvoiceRepository;
import com.example.Invoice.Service.InvoiceService;
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
    TableClient tableClient;

    Invoice findById(String invoiceID){
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(()->new AppException(ErrolCode.INVOICE_NO_EXITS));
    }
    //Trả về true nếu ko có table
    boolean checkTableID(String tableID){
        return tableClient.findById(tableID) == null;
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
