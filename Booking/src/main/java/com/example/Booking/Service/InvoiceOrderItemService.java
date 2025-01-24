package com.example.Booking.Service;

import com.example.Booking.DTO.Response.MenuFindByIdResponse;
import com.example.Booking.Entity.Invoice;
import com.example.Booking.DTO.OrderItem;
import com.example.Booking.Entity.Menu;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Repository.InvoiceRepository;
import com.example.Booking.Repository.MenuRepository;
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
    MenuRepository   menuRepository;

    Invoice findById(String invoiceID){
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(()->new AppException(ErrolCode.ERROL_OTHER));
    }




    public Set<OrderItem> findAll(String invoiceID){
        Invoice invoice = findById(invoiceID);
        return invoice.getOrders();
    }

    //Save base on (invoiceID and tableID)
    public boolean save(String invoiceID,OrderItem orderItem){
        Invoice invoice = findById(invoiceID);

        menuRepository.findById(orderItem.getItemID())
                .orElseThrow(()->new AppException(ErrolCode.MENU_NO_EXISTS));

        if(invoice.getOrders().contains(orderItem)){
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
                    Menu menu = menuRepository.findById(orderItem.getItemID())
                            .orElseThrow(()->new AppException(ErrolCode.MENU_NO_EXISTS));
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
