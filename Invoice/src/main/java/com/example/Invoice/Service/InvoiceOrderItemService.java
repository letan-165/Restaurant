package com.example.Invoice.Service;

import com.example.Invoice.DTO.Response.MenuFindByIdResponse;
import com.example.Invoice.Entity.Invoice;
import com.example.Invoice.Entity.OrderItem;
import com.example.Invoice.Exception.AppException;
import com.example.Invoice.Exception.ErrolCode;
import com.example.Invoice.Mapper.InvoiceMapper;
import com.example.Invoice.Repository.HttpClient.MenuClient;
import com.example.Invoice.Repository.InvoiceRepository;
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
public class InvoiceOrderItemService {
    InvoiceRepository invoiceRepository;
    MenuClient menuClient;

    Invoice findById(String invoiceID){
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(()->new AppException(ErrolCode.INVOICE_NO_EXITS));
    }
    //Trả về true nếu ko có menu
    boolean checkMenu(String itemID){
        return menuClient.findById(itemID) == null;
    }

    public Set<OrderItem> findAll(String invoiceID){
        Invoice invoice = findById(invoiceID);
        return invoice.getOrders();
    }

    //Save base on (invoiceID and tableID)
    public boolean save(String invoiceID,OrderItem orderItem){
        Invoice invoice = findById(invoiceID);
        if(invoice.getOrders().contains(orderItem) || checkMenu(orderItem.getItemID())){
            return false;
        }
        invoice.getOrders().add(orderItem);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Save base on (invoiceID and list table)
    public boolean save(String invoiceID, Set<OrderItem> orders){
        Invoice invoice = findById(invoiceID);
        Long total = orders.stream()
                .mapToLong(orderItem -> {
                    MenuFindByIdResponse menu = menuClient.findById(orderItem.getItemID()).getResult();
                    if ("BIG".equals(orderItem.getSize())) {
                        return menu.getPriceBig()*orderItem.getQuantity();
                    } else {
                        return menu.getPriceSmall()*orderItem.getQuantity();
                    }
                })
                .sum();
        invoice.setTotal(total);
        invoice.setOrders(orders);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String invoiceID,String itemID){
        Invoice invoice = findById(invoiceID);
        OrderItem orderItem = invoice.getOrders().stream()
                                            .filter(item->item.getItemID().equals(itemID))
                                            .findFirst()
                                            .orElse(null);
        if(orderItem!=null && !invoice.getOrders().contains(orderItem)){
            return false;
        }
        invoice.getOrders().remove(orderItem);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
