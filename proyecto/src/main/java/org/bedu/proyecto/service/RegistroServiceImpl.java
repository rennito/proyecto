package org.bedu.proyecto.service;

import org.bedu.proyecto.model.Registro;
import org.bedu.proyecto.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public List<Registro> obtenerTodosLosRegistros() {
        return registroRepository.findAll();
    }

    @Override
    public Optional<Registro> findByNumeroId(String numeroId) {
        return registroRepository.findByNumeroId(numeroId);
    }

    @Override
    public void save(Registro registro) {
        registroRepository.save(registro);
    }
}
