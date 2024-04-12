package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.ContabilizacaoService;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/fechada")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<Page<PautaEmAndamentoDTO>> buscarPautasFechadas(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho
    ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasFechadas(pageable);
        Page<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/fechada/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas por categoria.")
    public ResponseEntity<Page<PautaEmAndamentoDTO>> buscarPautasFechadasPorCategoria(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho,
            @PathVariable("categoria") Categoria categoria
            ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasFechadasPorCategoria(categoria, pageable);
        Page<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/aberta/")
    @Operation(summary = "Obtêm uma página de pautas abertas.")
    public ResponseEntity<Page<PautaEmAndamentoDTO>> buscarPautasAbertas(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho
    ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasAbertas(pageable);
        Page<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/aberta/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas abertas por categoria")
    public ResponseEntity<Page<PautaEmAndamentoDTO>> buscarPautasAbertasPorCategoria(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho,
            @PathVariable("categoria") Categoria categoria
    ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasAbertasPorCategoria(categoria, pageable);
        Page<PautaEmAndamentoDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaEmAndamentoDTO(pautas);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizada/")
    @Operation(summary = "Obtêm uma página de pautas finalizadas")
    public ResponseEntity<Page<PautaFinalizadaDTO>> buscarPautasFinalizadas(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho
    ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasFinalizadas(pageable);
        Page<PautaFinalizadaDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaFinalizadaDTO(pautas, contabilizacaoService);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

    @GetMapping("/finalizada/{categoria}")
    @Operation(summary = "Obtêm uma página de pautas fechadas")
    public ResponseEntity<Page<PautaFinalizadaDTO>> buscarPautasFinalizadasPorCategoria(
            @RequestParam(defaultValue = "0") final Integer pagina,
            @RequestParam(defaultValue = "10") final Integer tamanho,
            @PathVariable("categoria") Categoria categoria
    ){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pauta> pautas = pautaService.obterPautasFinalizadasPorCategoria(categoria, pageable);
        Page<PautaFinalizadaDTO> pautasDTOs = PautaMapper.mapPageOfPautaToPageOfPautaFinalizadaDTO(pautas, contabilizacaoService);
        return ResponseEntity.status(HttpStatus.OK).body(pautasDTOs);
    }

}
