package com.example.springbootvalidation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
    private String message;

    private int status;

    private T body;
}
