package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;

    @Override
    public boolean salvar(Pauta pauta) {
        pautaRepository.save(pauta);
        return true;
    }

}
