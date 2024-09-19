package org.bedu.proyecto.service;

import org.bedu.proyecto.model.Registro;

import java.util.List;
import java.util.Optional;

public interface RegistroService {
    List<Registro> obtenerTodosLosRegistros();
    Optional<Registro> findByNumeroId(String numeroId);
    void save(Registro registro);
}
