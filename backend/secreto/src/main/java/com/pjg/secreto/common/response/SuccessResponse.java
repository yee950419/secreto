package com.pjg.secreto.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class SuccessResponse {

    HttpStatus httpStatus;
    String msg;
    Object result;

    public SuccessResponse(HttpStatus httpStatus, String msg){
        this.httpStatus = httpStatus;
        this.msg = msg;
    }
}