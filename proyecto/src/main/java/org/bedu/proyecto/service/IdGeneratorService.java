package org.bedu.proyecto.service;

import org.bedu.proyecto.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

    @Autowired
    private RegistroRepository registroRepository;

    public String generarNumeroId() {
        Long maxId = registroRepository.findMaxId(); // Obtén el ID máximo actual
        Long newId = (maxId == null) ? 1 : maxId + 1; // Incrementa el ID máximo
        return String.format("%05d", newId); // Formatea el ID con ceros a la izquierda
    }
}
