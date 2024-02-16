package com.demo.ecomApp.dto;

import lombok.Data;

@Data
public class ExceptionDTO {
    private String messageId;
    private String text;
    private String variables;
}
