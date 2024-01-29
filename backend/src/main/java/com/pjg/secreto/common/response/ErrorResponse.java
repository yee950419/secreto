package com.pjg.secreto.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private final HttpStatus status;
    private final String message;


}
