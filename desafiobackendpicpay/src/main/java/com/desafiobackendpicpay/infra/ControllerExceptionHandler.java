package com.desafiobackendpicpay.infra;

import com.desafiobackendpicpay.dtos.ExcepitionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExcepitionDTO excepitionDTO = new ExcepitionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(excepitionDTO);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExcepitionDTO excepitionDTO = new ExcepitionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(excepitionDTO);
        }
}
