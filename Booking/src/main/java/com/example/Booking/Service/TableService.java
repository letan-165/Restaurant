package com.example.Booking.Service;

import com.example.Booking.DTO.Request.TableSaveRequest;
import com.example.Booking.DTO.Response.TableFindByIdResponse;
import com.example.Booking.DTO.Response.TableUpdateResponse;
import com.example.Booking.Entity.Table;
import com.example.Booking.Exception.AppException;
import com.example.Booking.Exception.ErrolCode;
import com.example.Booking.Mapper.TableMapper;
import com.example.Booking.Repository.TableRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class TableService {
    TableRepository tableRepository;
    TableMapper tableMapper;

    public List<Table>findAll(){
        return tableRepository.findAll();
    }

    public boolean save(TableSaveRequest request){
        if(tableRepository.existsById(request.getTableID())){
            throw new AppException(ErrolCode.TABLE_HAD_BOOKING);
        }
        tableRepository.save(tableMapper.toTable(request));
        return true;
    }

    public TableFindByIdResponse findById(String tableID){
        return tableMapper.toTableFindByIdResponse(tableRepository.findById(tableID)
                .orElseThrow(()->new AppException(ErrolCode.TABLE_NO_EXISTS)));
    }

    public boolean deleteById(String tableID){
        if(!tableRepository.existsById(tableID)){
            throw new AppException(ErrolCode.TABLE_NO_EXISTS);
        }
        tableRepository.deleteById(tableID);
        return true;
    }

    public boolean update(String tableID, TableUpdateResponse request){
        if(!tableRepository.existsById(tableID)){
            throw new AppException(ErrolCode.TABLE_NO_EXISTS);
        }
        tableRepository.save(tableMapper.toTable(tableID, request));
        return true;
    }

}
