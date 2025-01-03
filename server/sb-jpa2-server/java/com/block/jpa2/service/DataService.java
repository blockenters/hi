package com.block.jpa2.service;

import com.block.jpa2.dto.DataListResponse;
import com.block.jpa2.dto.DataRequest;
import com.block.jpa2.dto.DataResponse;
import com.block.jpa2.entity.Data;
import com.block.jpa2.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    @Autowired
    DataRepository dataRepository;

    public void createData(DataRequest dataRequest){
        // DTO 를 Entity로 변환
        Data data = new Data();
        data.name = dataRequest.name;
        data.content = dataRequest.content;

        dataRepository.save(data);
    }

    public DataResponse getDataById(long id){

        Optional<Data> data = dataRepository.findById(id);
        // Entity를 DTO로 변환
        DataResponse dataResponse = new DataResponse();

        if(data.isPresent()){
            dataResponse.item = data.get();
        }else{
            dataResponse.item = null;
        }
        return dataResponse;
    }

    public DataListResponse getAllData(){
        List<Data> dataList = dataRepository.findAll();
        // Entity를 DTO로 변환
        DataListResponse dataListResponse = new DataListResponse();
        dataListResponse.itemList = dataList;
        return dataListResponse;
    }

}





