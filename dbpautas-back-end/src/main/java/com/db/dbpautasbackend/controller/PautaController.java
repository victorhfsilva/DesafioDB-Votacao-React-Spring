package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.PautaService;
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
@RequestMapping("/pauta")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;

    @PostMapping("/registrar")
    @Operation(summary = "Registra uma pauta no sistema.")
    public ResponseEntity<Boolean> registrar(@RequestBody @Valid RegistrarPautaDTO pautaDTO) {
        Pauta pauta = PautaMapper.mapRegistrarPautaDTOtoPauta(pautaDTO);
        boolean salvo = pautaService.salvar(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}