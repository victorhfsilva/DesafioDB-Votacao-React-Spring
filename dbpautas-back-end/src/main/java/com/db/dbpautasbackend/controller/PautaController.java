package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;

    @PostMapping("/registrar")
    @Operation(summary = "Registra uma pauta no sistema.")
    public ResponseEntity<Boolean> registrar(@RequestBody @Valid RegistrarPautaDTO pautaDTO) {
        Pauta pauta = PautaMapper.mapRegistrarPautaDTOtoPauta(pautaDTO);
        pautaService.salvar(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PatchMapping("/abrir/{id}")
    @Operation(summary = "Abre uma pauta já registrada para votação.")
    public ResponseEntity<Boolean> abrir(
            @PathVariable("id") Long id,
            @RequestParam(value = "minutos", required = false) Integer tempoDeSessaoEmMinutos){
        pautaService.abrirPauta(id, tempoDeSessaoEmMinutos);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PatchMapping("/votar/{id}")
    @Operation(summary = "Vota uma pauta aberta. Cada usuário só pode votar uma vez.")
    public ResponseEntity<Boolean> votar(
            @PathVariable("id") Long id,
            @RequestParam(value = "voto") Voto voto
            ){
        boolean votadoComSucesso = pautaService.votarPauta(id, voto);
        return ResponseEntity.status(HttpStatus.OK).body(votadoComSucesso);
    }
}
