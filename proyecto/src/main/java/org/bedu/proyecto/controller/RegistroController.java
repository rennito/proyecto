package org.bedu.proyecto.controller;

import org.bedu.proyecto.model.Registro;
import org.bedu.proyecto.repository.RegistroRepository;
import org.bedu.proyecto.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
public class RegistroController {

    @Autowired
    private RegistroRepository repository;

    @Autowired
    private IdGeneratorService idGeneratorService;
    

    @PostMapping("/guardarRegistro")
    public String save(@Valid @ModelAttribute("registro") Registro registro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }
        
        // Verificar si ya existe un registro con el mismo numeroId
        if (registro.getNumeroId() == null || registro.getNumeroId().isEmpty()) {
            registro.setNumeroId(idGeneratorService.generarNumeroId());
        } else if (repository.findByNumeroId(registro.getNumeroId()).isPresent()) {
            model.addAttribute("mensajeError", "El número ID ya está registrado.");
            return "registro";
        }
    
        try {
            // Guardar el objeto registro
            repository.save(registro);
            model.addAttribute("mensaje", "Registro guardado exitosamente");
            return "registro";
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Ocurrió un error al registrar el alumno.");
            return "registro";
        }
    }


        // Método para eliminar un registro
        @PostMapping("/eliminarRegistro/{id}")
        public String eliminarRegistro(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
            try {
                // Eliminar el registro por su ID
                repository.deleteById(id);
                redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado exitosamente.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("mensajeError", "Ocurrió un error al eliminar el registro.");
            }
            
             return "redirect:/tables"; 
        }
        
    
}
