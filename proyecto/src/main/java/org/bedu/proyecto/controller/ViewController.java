package org.bedu.proyecto.controller;

import java.util.List;

import org.bedu.proyecto.model.Registro;
import org.bedu.proyecto.repository.RegistroRepository;
import org.bedu.proyecto.service.IdGeneratorService;
import org.bedu.proyecto.service.RegistroService; // Asegúrate de importar el servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private RegistroService registroService; // Inyección de dependencias para el servicio

@Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private RegistroRepository repository;

    @GetMapping("/")
    public String goHome(Model model) {
        model.addAttribute("titulo", "Bienvenid@, página de inicio");
        return "inicio";
    }

    @GetMapping("/registro")
    public String showForm(Model model) {
        Registro registro = new Registro();
        // Generar un nuevo número ID para el registro
        registro.setNumeroId(idGeneratorService.generarNumeroId());
        model.addAttribute("registro", registro);
        return "registro.html"; 
    }

    @GetMapping("/tables")
    public String listarRegistros(Model model) {
        // Obtener todos los registros desde el servicio
        List<Registro> registros = registroService.obtenerTodosLosRegistros();
        model.addAttribute("registros", registros);
        return "tables.html"; 
    }
        @GetMapping("/detalle/{id}")
    public String detalleRegistro(@PathVariable("id") Long id, Model model) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado con ID: " + id));
        model.addAttribute("registro", registro);
        return "details.html";
    }

    
}
