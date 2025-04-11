package com.br.otto.financeiroestudoback.config;

import com.br.otto.financeiroestudoback.dto.MensagemErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MensagemErroDTO> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new MensagemErroDTO(ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroDTO> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MensagemErroDTO("Erro interno no servidor: " + ex.getMessage()));
    }

}