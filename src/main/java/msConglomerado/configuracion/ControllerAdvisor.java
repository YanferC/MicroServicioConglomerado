package msConglomerado.configuracion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import msConglomerado.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConglomeradoNotServiceUnavailable.class)
    public ResponseEntity<Map<String, String>> conglomeradoNotServiceUnavailabl(ConglomeradoNotServiceUnavailable conglomeradoNotServiceUnavailable) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.INTERNAL_SERVER_ERROR_MESSAGE));
    }

    @ExceptionHandler(ConglomeradoNotSavedException.class)
    public ResponseEntity<Map<String, String>> conglomeradoNotSavedException(ConglomeradoNotSavedException conglomeradoNotSavedException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.RESPUESTA_MESSAGE, Constants.MENSAJE_FACTURA_NOT_SAVED));
    }

    @ExceptionHandler(ConglomeradoNotDeleteException.class)
    public ResponseEntity<Map<String, String>> conglomeradoNotDeleteException(ConglomeradoNotDeleteException conglomeradoNotDeleteException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.MENSAJE_FACTURA_NOT_DELETED));
    }

    @ExceptionHandler(ConglomeradoNotEditException.class)
    public ResponseEntity<Map<String, String>> conglomeradoNotEditException(ConglomeradoNotEditException conglomeradoNotEditException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.MENSAJE_FACTURA_NOT_EDITED));
    }

    @ExceptionHandler(ConglomeradoNotFoundException.class)
    public ResponseEntity<Map<String, String>> conglomeradoNotFoundException(ConglomeradoNotFoundException conglomeradoNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.NO_DATA_FOUND));
    }
}
