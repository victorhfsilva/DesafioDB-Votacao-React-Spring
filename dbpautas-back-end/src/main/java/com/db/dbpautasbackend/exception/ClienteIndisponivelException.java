package com.db.dbpautasbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ClienteIndisponivelException extends RuntimeException{
    private String mensagem;
}
