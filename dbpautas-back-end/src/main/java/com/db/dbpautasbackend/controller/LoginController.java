package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.service.interfaces.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("")
    @Operation(summary = "Faz login na api e obtém um token JWT.")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO login) {
        String token = loginService.gerarToken(login);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
