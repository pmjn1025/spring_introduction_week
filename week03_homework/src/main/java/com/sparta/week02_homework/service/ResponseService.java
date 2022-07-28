package com.sparta.week02_homework.service;

import com.sparta.week02_homework.models.CommonResponse;
import com.sparta.week02_homework.models.ListResponse;
import com.sparta.week02_homework.models.SingleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public<T> SingleResponse<T> getSingleResponse(T data){

        SingleResponse singleResponse = new SingleResponse();
        singleResponse.data = data;
        setSuccessResponse(singleResponse);

        return singleResponse;

    }

    public<T> ListResponse<T> getListResponse(List<T> dataList){

        ListResponse listResponse = new ListResponse();
        listResponse.datalist = dataList;
        setSuccessResponse(listResponse);

        return listResponse;
    }



    void setSuccessResponse(CommonResponse response){

        response.code=0;
        response.success = true;
        response.message = "Success";

    }


    void setFailureResponse(CommonResponse response){

        response.code=500;
        response.success = false;
        response.message = "Failure";

    }

}
