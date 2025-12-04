package com.beasthr10.productserviceecomwebsite.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidProductCreationException extends Exception {
    public InvalidProductCreationException(String message) {
        super(message);
    }
}
