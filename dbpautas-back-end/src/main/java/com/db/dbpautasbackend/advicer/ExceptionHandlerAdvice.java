package com.db.dbpautasbackend.advicer;

import com.db.dbpautasbackend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ResponseEntity.badRequest().body("O campo " + fieldError.getField() + " é inválido.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("Esperava-se uma entrada com tipo diferente.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body("Parâmetro ausente.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CpfIrregularException.class)
    public ResponseEntity<String> handleCpfIrregularException(CpfIrregularException ex) {
        return ResponseEntity.badRequest().body("O estado deste cpf é irregular.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VotoInvalidoException.class)
    public ResponseEntity<String> handleVotoInvalidoException(VotoInvalidoException ex) {
        return ResponseEntity.badRequest().body("Você já votou nesta pauta.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Ocorreu um erro ao converter sua mensagem.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PautaAbertaException.class)
    public ResponseEntity<String> handlePautaAbertaException(PautaAbertaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Esta pauta já foi aberta.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PautaFechadaException.class)
    public ResponseEntity<String> handlePautaFechadaException(PautaFechadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Esta pauta se encontra fechada.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PautaFinalizadaException.class)
    public ResponseEntity<String> handlePautaFinalizadaException(PautaFinalizadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("A votação desta pauta já foi finalizada.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O recurso solicitado não foi encontrado.");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ClienteIndisponivelException.class)
    public ResponseEntity<String> handleClienteIndisponivelException(ClienteIndisponivelException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Não foi possível validar dados no momento.");
    }
}
