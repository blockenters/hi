package com.block.jpa2.controller;

import com.block.jpa2.dto.DataListResponse;
import com.block.jpa2.dto.DataRequest;
import com.block.jpa2.dto.DataResponse;
import com.block.jpa2.entity.Data;
import com.block.jpa2.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/data")
    public ResponseEntity<Void> createData(@RequestBody DataRequest dataRequest){
        dataService.createData(dataRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<DataResponse> getDataById(@PathVariable long id){

       DataResponse dataResponse = dataService.getDataById(id);
       return ResponseEntity.status(200).body(dataResponse);
    }

    @GetMapping("/data")
    public ResponseEntity<DataListResponse> getAllData(){
        DataListResponse dataListResponse = dataService.getAllData();
        return ResponseEntity.status(200).body(dataListResponse);
    }

}
