package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.enums.Categoria;
import com.db.dbpautasbackend.model.enums.Voto;
import com.db.dbpautasbackend.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;

    @PostMapping("")
    @Operation(summary = "Registra uma pauta no sistema.")
    public ResponseEntity<Boolean> registrar(@RequestBody @Valid RegistrarPautaRequest pautaDTO) {
        pautaService.salvar(pautaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Abre uma pauta já registrada para votação.")
    public ResponseEntity<Boolean> abrir(
            @PathVariable("id") Long id,
            @RequestParam(value = "minutos", required = false) Integer tempoDeSessaoEmMinutos){
        pautaService.abrirPauta(id, tempoDeSessaoEmMinutos);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PatchMapping("/{id}/votos")
    @Operation(summary = "Vota uma pauta aberta. Cada usuário só pode votar uma vez.")
    public ResponseEntity<Boolean> votar(
            @PathVariable("id") Long id,
            @RequestParam(value = "voto") Voto voto
            ){
        pautaService.votarPauta(id, voto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/fechadas/")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<List<PautaEmAndamentoResponse>> buscarPautasFechadas(
    ){
        List<PautaEmAndamentoResponse> pautasDTOs = pautaService.obterPautasFechadas();
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/fechadas/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas por categoria.")
    public ResponseEntity<List<PautaEmAndamentoResponse>> buscarPautasFechadasPorCategoria(
            @PathVariable("categoria") Categoria categoria
            ){
        List<PautaEmAndamentoResponse> pautasDTOs = pautaService.obterPautasFechadasPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/abertas/")
    @Operation(summary = "Obtêm uma página de pautas abertas.")
    public ResponseEntity<List<PautaEmAndamentoResponse>> buscarPautasAbertas(
    ){
        List<PautaEmAndamentoResponse> pautasDTOs = pautaService.obterPautasAbertas();
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/abertas/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas abertas por categoria")
    public ResponseEntity<List<PautaEmAndamentoResponse>> buscarPautasAbertasPorCategoria(
            @PathVariable("categoria") Categoria categoria
    ){
        List<PautaEmAndamentoResponse> pautasDTOs = pautaService.obterPautasAbertasPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizadas/")
    @Operation(summary = "Obtêm uma página de pautas finalizadas")
    public ResponseEntity<List<PautaFinalizadaResponse>> buscarPautasFinalizadas(
    ){
        List<PautaFinalizadaResponse> pautasDTOs = pautaService.obterPautasFinalizadas();
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizadas/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<List<PautaFinalizadaResponse>> buscarPautasFinalizadasPorCategoria(
            @PathVariable("categoria") Categoria categoria
    ){
        List<PautaFinalizadaResponse> pautasDTOs = pautaService.obterPautasFinalizadasPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

}
