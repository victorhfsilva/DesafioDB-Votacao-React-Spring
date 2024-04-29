package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.RegistrarUsuarioRequest;
import com.db.dbpautasbackend.service.UsuarioService;
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
@RequestMapping(value = "/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("")
    @Operation(summary = "Registra um usuário no sistema.")
    public ResponseEntity<Boolean> registrar(@RequestBody @Valid RegistrarUsuarioRequest registrarUsuarioDTO) {
        usuarioService.salvar(registrarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
