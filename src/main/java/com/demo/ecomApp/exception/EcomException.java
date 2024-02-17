package com.demo.ecomApp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcomException extends RuntimeException {
        private String messageId;
        private String text;
        private String variables;
}
