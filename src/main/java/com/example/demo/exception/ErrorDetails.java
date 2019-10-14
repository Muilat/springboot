package com.example.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private int code;

    public static int NOT_VALID_CODE = 10;
    public static int SUCCESS_CODE = 0;
    public static int NOT_FOUND_CODE = 99;


    public ErrorDetails(Date timestamp, String message, String details, int code) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.code = code;
    }

}