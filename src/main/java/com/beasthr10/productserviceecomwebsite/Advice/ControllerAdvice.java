package com.beasthr10.productserviceecomwebsite.Advice;


import com.beasthr10.productserviceecomwebsite.Exception.InvalidProductCreationException;
import com.beasthr10.productserviceecomwebsite.Exception.InvalidProductcall;
import com.beasthr10.productserviceecomwebsite.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(){
        ErrorDTO errordto = new ErrorDTO();
        errordto.setErrormessage("Invalid Input");
        errordto.setErrorstatus(400);
        return ResponseEntity.badRequest().body(errordto);
    }
    // DTO check exception return
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handelValidationError(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult().getFieldError().getDefaultMessage();

        InvalidProductCreationException invalidProductCreationException = new InvalidProductCreationException(message);

        ErrorDTO errordto = new ErrorDTO();
        errordto.setErrormessage(invalidProductCreationException.getMessage());
        errordto.setErrorstatus(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errordto);
    }

    // Invalid product call exception return
    @ExceptionHandler(InvalidProductCreationException.class)
    public ResponseEntity<ErrorDTO> handelInvalidProductCreationException(InvalidProductCreationException ex){


        ErrorDTO errordto = new ErrorDTO();
        errordto.setErrormessage(ex.getMessage());
        errordto.setErrorstatus(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errordto);
    }

    @ExceptionHandler(InvalidProductcall.class)
    public ResponseEntity<ErrorDTO> handelInvalidProductcall(InvalidProductcall ex){


        ErrorDTO errordto = new ErrorDTO();
        errordto.setErrormessage(ex.getMessage());
        errordto.setErrorstatus(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errordto);
    }

}
