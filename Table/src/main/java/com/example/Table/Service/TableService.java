package com.example.Table.Service;

import com.example.Table.DTO.Request.TableSaveRequest;
import com.example.Table.DTO.Response.TableFindByIdResponse;
import com.example.Table.DTO.Response.TableUpdateResponse;
import com.example.Table.Entity.Table;
import com.example.Table.Exception.AppException;
import com.example.Table.Exception.ErrolCode;
import com.example.Table.Mapper.TableMapper;
import com.example.Table.Repository.TableRepository;
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
            throw new AppException(ErrolCode.NAME_EXITS);
        }
        tableRepository.save(tableMapper.toTable(request));
        return true;
    }

    public TableFindByIdResponse findById(String tableID){
        if(!tableRepository.existsById(tableID)){
            throw new AppException(ErrolCode.NAME_NO_EXITS);
        }
        return tableMapper.toTableFindByIdResponse(tableRepository.findById(tableID)
                .orElseThrow(()->new AppException(ErrolCode.NAME_NO_EXITS)));
    }

    public boolean deleteById(String tableID){
        if(!tableRepository.existsById(tableID)){
            throw new AppException(ErrolCode.NAME_NO_EXITS);
        }
        tableRepository.deleteById(tableID);
        return true;
    }

    public boolean update(String tableID, TableUpdateResponse request){
        if(!tableRepository.existsById(tableID)){
            throw new AppException(ErrolCode.NAME_NO_EXITS);
        }
        tableRepository.save(tableMapper.toTable(tableID, request));
        return true;
    }

}
