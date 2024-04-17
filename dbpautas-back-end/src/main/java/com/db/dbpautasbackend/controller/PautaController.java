package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.ContabilizacaoService;
import com.db.dbpautasbackend.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pauta")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;
    private ContabilizacaoService contabilizacaoService;

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
        pautaService.votarPauta(id, voto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/fechada/")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<List<PautaEmAndamentoDTO>> buscarPautasFechadas(
    ){
        List<Pauta> pautas = pautaService.obterPautasFechadas();
        List<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/fechada/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas por categoria.")
    public ResponseEntity<List<PautaEmAndamentoDTO>> buscarPautasFechadasPorCategoria(
            @PathVariable("categoria") Categoria categoria
            ){
        List<Pauta> pautas = pautaService.obterPautasFechadasPorCategoria(categoria);
        List<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/aberta/")
    @Operation(summary = "Obtêm uma página de pautas abertas.")
    public ResponseEntity<List<PautaEmAndamentoDTO>> buscarPautasAbertas(
    ){
        List<Pauta> pautas = pautaService.obterPautasAbertas();
        List<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/aberta/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas abertas por categoria")
    public ResponseEntity<List<PautaEmAndamentoDTO>> buscarPautasAbertasPorCategoria(
            @PathVariable("categoria") Categoria categoria
    ){
        List<Pauta> pautas = pautaService.obterPautasAbertasPorCategoria(categoria);
        List<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizada/")
    @Operation(summary = "Obtêm uma página de pautas finalizadas")
    public ResponseEntity<List<PautaFinalizadaDTO>> buscarPautasFinalizadas(
    ){
        List<Pauta> pautas = pautaService.obterPautasFinalizadas();
        List<PautaFinalizadaDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaFinalizadaDTO(pautas, contabilizacaoService);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizada/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<List<PautaFinalizadaDTO>> buscarPautasFinalizadasPorCategoria(
            @PathVariable("categoria") Categoria categoria
    ){
        List<Pauta> pautas = pautaService.obterPautasFinalizadasPorCategoria(categoria);
        List<PautaFinalizadaDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaFinalizadaDTO(pautas, contabilizacaoService);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

}
