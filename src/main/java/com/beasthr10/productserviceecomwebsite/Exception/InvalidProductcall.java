package com.beasthr10.productserviceecomwebsite.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidProductcall extends Exception {
    public InvalidProductcall(String message) {
        super(message);
    }
}
