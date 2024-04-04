package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;

    @Override
    public boolean salvar(Pauta pauta) {
        pautaRepository.save(pauta);
        return true;
    }

    @Override
    public boolean abrirPauta(Long id, Integer tempoDeSessaoEmMinutos) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        pauta.setAberta(true);
        pauta.setAbertoAs(LocalDateTime.now());
        pauta.setTempoDeSessaoEmMinutos(tempoDeSessaoEmMinutos != null ? tempoDeSessaoEmMinutos : 1);
        pautaRepository.save(pauta);
        return true;
    }

}
