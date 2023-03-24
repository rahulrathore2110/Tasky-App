package com.tasky.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MyErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
