package com.example.Invoice.Repository.HttpClient;

import com.example.Invoice.DTO.Response.TableFindByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "table-service", url = "${app.service.table}")
public interface TableClient {

    @PostMapping(value = "/{tableID}",produces = MediaType.APPLICATION_JSON_VALUE)
    TableFindByIdResponse findById(@PathVariable String tableID);

}
