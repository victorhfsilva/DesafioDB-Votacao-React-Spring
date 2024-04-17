package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.RegistrarUsuarioDTO;
import com.db.dbpautasbackend.mapper.UsuarioMapper;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/registrar")
    @Operation(summary = "Registra um usuário no sistema.")
    public ResponseEntity<Boolean> registrar(@RequestBody @Valid RegistrarUsuarioDTO registrarUsuarioDTO) {
        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioDTOToUsuario(registrarUsuarioDTO, passwordEncoder);
        usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
